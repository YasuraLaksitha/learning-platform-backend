package edu.opl.backend.repository;

import edu.opl.backend.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstructorRepository extends JpaRepository<InstructorEntity, UUID> {
    Boolean existsByEmail(String email);

    InstructorEntity findByUsername(String username);
}
