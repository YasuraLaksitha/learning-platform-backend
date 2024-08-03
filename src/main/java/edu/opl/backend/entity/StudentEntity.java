package edu.opl.backend.entity;

import jakarta.persistence.*;
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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id",foreignKey = @ForeignKey(name = "FK_student_course")),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<CourseEntity> courseEntitySet;
}
