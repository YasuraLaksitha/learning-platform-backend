package edu.opl.backend.entity;

import edu.opl.backend.dto.Course;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class InstructorEntity extends PersonEntity {
    private String bio;
    private String specialization;
    private String experience;
    private boolean isAvailable;

    @OneToMany(mappedBy = "instructorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseEntity> courseEntitySet;
}
