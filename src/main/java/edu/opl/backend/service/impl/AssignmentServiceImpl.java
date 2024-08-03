package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.dto.Assignment;
import edu.opl.backend.entity.AssignmentEntity;
import edu.opl.backend.repository.AssignmentRepostitory;
import edu.opl.backend.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepostitory assignmentRepostitory;

    private final ObjectMapper mapper;

    @Override
    public Assignment create(Assignment assignment) {
        final AssignmentEntity entity = assignmentRepostitory.save(mapper.convertValue(assignment, AssignmentEntity.class));
        return mapper.convertValue(entity, Assignment.class);
    }

    @Override
    public Assignment findById(UUID uuid) {
        final Optional<AssignmentEntity> entity = assignmentRepostitory.findById(uuid);
        return entity.map(assignmentEntity -> mapper.convertValue(assignmentEntity, Assignment.class)).orElse(null);
    }

    @Override
    public List<Assignment> findAll() {
        return assignmentRepostitory.findAll().stream()
                .map(assignmentEntity -> mapper.convertValue(assignmentEntity, Assignment.class)).toList();
    }

    @Override
    public void update(Assignment assignment) {
        this.create(assignment);
    }

    @Override
    public void delete(Assignment assignment) {
        assignmentRepostitory.delete(mapper.convertValue(assignment, AssignmentEntity.class));
    }
}
