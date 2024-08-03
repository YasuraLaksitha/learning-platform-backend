package edu.opl.backend.repository;

import edu.opl.backend.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
}
