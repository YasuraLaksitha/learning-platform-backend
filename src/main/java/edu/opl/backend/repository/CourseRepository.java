package edu.opl.backend.repository;

import edu.opl.backend.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    Boolean existsByTitle(String title);
}
