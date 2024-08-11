package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.config.AssignmentValidator;
import edu.opl.backend.dto.Assignment;
import edu.opl.backend.entity.AssignmentEntity;
import edu.opl.backend.exception.EmptyValuePassedException;
import edu.opl.backend.exception.EntityNotFoundException;
import edu.opl.backend.repository.AssignmentRepository;
import edu.opl.backend.service.AssignmentService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    public static final String NOT_FOUND = "Assignment with id %s not found";

    private final AssignmentRepository assignmentRepository;

    private final ObjectMapper mapper;

    private final AssignmentValidator assignmentValidator;

    @Override
    public Assignment create(Assignment assignment) {
        assignmentValidator.isValidAssignment(assignment, true);
        if (Boolean.TRUE.equals(assignmentRepository
                .existsByTitleAndAllocatedDate(assignment.getTitle(),assignment.getAllocatedDate())))
            throw new EntityExistsException("Assignment already exists");
        final AssignmentEntity entity = assignmentRepository
                .save(mapper.convertValue(assignment, AssignmentEntity.class));
        return mapper.convertValue(entity, Assignment.class);
    }

    @Override
    public Assignment findById(Long Long) {
        if (!StringUtils.hasText(Long.toString()))
            throw new EmptyValuePassedException("ID is not provided");
        final Optional<AssignmentEntity> entity = assignmentRepository.findById(Long);
        if (entity.isEmpty())
            throw new EntityNotFoundException(String.format(NOT_FOUND, Long));
        return mapper.convertValue(entity, Assignment.class);
    }

    @Override
    public List<Assignment> findAll() {
        return assignmentRepository.findAll().stream()
                .map(assignmentEntity -> mapper.convertValue(assignmentEntity, Assignment.class)).toList();
    }

    @Override
    public Assignment update(Assignment assignment) {
        assignmentValidator.isValidAssignment(assignment, false);
        if (!assignmentRepository.existsById(assignment.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, assignment.getId()));
        return mapper.convertValue(assignmentRepository
                .save(mapper.convertValue(assignment, AssignmentEntity.class)), Assignment.class);
    }

    @Override
    public void delete(Assignment assignment) {
        assignmentValidator.isValidAssignment(assignment, false);
        if (!assignmentRepository.existsById(assignment.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, assignment.getId()));
        assignmentRepository.delete(mapper.convertValue(assignment, AssignmentEntity.class));
    }
}
