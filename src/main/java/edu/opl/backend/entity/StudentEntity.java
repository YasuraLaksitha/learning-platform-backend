package edu.opl.backend.entity;

import edu.opl.backend.util.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "student")
@EqualsAndHashCode(callSuper = true)
public non-sealed class StudentEntity extends PersonEntity {

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"),
            foreignKey = @ForeignKey(name = "FKStudentCourseId"),
            inverseForeignKey = @ForeignKey(name = "FKCourseStudentId")
    )
    private Set<CourseEntity> courseEntitySet;

    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AssignmentEntity> assignmentEntitySet;
}
