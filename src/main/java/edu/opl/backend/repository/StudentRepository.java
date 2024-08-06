package edu.opl.backend.repository;

import edu.opl.backend.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    Boolean existsByEmail(String email);
    StudentEntity findByUsername(String username);
}
