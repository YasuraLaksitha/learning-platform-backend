package edu.opl.backend.repository;

import edu.opl.backend.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
    Boolean existsByTitleAndAllocatedDate(String title, LocalDate allocatedDate);
}
