package edu.opl.backend.repository;

import edu.opl.backend.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<InstructorEntity, Integer> {
}
