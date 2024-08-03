package edu.opl.backend.repository;

import edu.opl.backend.entity.CourseEntity;
import edu.opl.backend.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
}
