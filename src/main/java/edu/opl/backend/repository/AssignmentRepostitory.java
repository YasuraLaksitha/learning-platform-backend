package edu.opl.backend.repository;

import edu.opl.backend.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssignmentRepostitory extends JpaRepository<AssignmentEntity, UUID> {
}
