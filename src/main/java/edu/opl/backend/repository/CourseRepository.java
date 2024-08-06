package edu.opl.backend.repository;

import edu.opl.backend.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    Boolean existsByTitle(String title);
}
