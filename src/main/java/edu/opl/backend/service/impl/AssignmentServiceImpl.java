package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.config.AssignmentValidator;
import edu.opl.backend.dto.Assignment;
import edu.opl.backend.entity.AssignmentEntity;
import edu.opl.backend.exception.EmptyValuePassedException;
import edu.opl.backend.exception.EntityNotFoundException;
import edu.opl.backend.repository.AssignmentRepostitory;
import edu.opl.backend.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    public static final String NOT_FOUND = "Assignment with id %s not found";

    private final AssignmentRepostitory assignmentRepostitory;

    private final ObjectMapper mapper;

    private final AssignmentValidator assignmentValidator;

    @Override
    public Assignment create(Assignment assignment) {
        assignmentValidator.isValidAssignment(assignment, true);
        final AssignmentEntity entity = assignmentRepostitory.save(mapper.convertValue(assignment, AssignmentEntity.class));
        return mapper.convertValue(entity, Assignment.class);
    }

    @Override
    public Assignment findById(UUID uuid) {
        if (!StringUtils.hasText(uuid.toString()))
            throw new EmptyValuePassedException("ID is not provided");
        final Optional<AssignmentEntity> entity = assignmentRepostitory.findById(uuid);
        if (entity.isEmpty())
            throw new EntityNotFoundException(String.format(NOT_FOUND, uuid));
        return mapper.convertValue(entity, Assignment.class);
    }

    @Override
    public List<Assignment> findAll() {
        return assignmentRepostitory.findAll().stream()
                .map(assignmentEntity -> mapper.convertValue(assignmentEntity, Assignment.class)).toList();
    }

    @Override
    public Assignment update(Assignment assignment) {
        assignmentValidator.isValidAssignment(assignment, false);
        if (!assignmentRepostitory.existsById(assignment.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, assignment.getId()));
        return mapper.convertValue(assignmentRepostitory
                .save(mapper.convertValue(assignment, AssignmentEntity.class)), Assignment.class);
    }

    @Override
    public void delete(Assignment assignment) {
        assignmentValidator.isValidAssignment(assignment, false);
        if (!assignmentRepostitory.existsById(assignment.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, assignment.getId()));
        assignmentRepostitory.delete(mapper.convertValue(assignment, AssignmentEntity.class));
    }
}
