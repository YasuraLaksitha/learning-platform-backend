package edu.opl.backend.repository;

import edu.opl.backend.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, UUID> {
    Boolean existsByTitleAndAllocatedDate(String title, LocalDate allocatedDate);
}
