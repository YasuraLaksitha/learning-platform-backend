package edu.opl.backend.entity;

import edu.opl.backend.dto.Course;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentEntity extends PersonEntity {
    private LocalDate dob;
    private String gender;

    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseEntity> courseEntitySet;
}
