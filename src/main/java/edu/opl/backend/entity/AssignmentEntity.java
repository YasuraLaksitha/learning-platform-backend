package edu.opl.backend.entity;

import edu.opl.backend.util.SubmitionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "assignment")
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String title;

    @Column(nullable = false)
    private String description;

    private LocalDate allocatedDate;

    private LocalDate dueDate;

    private SubmitionStatus status;

    @ManyToOne
    @JoinColumn(name = "student_id",foreignKey = @ForeignKey(name = "FKStudentId"))
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "course_id",foreignKey = @ForeignKey(name = "FKCourseId"))
    private CourseEntity courseEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_type", nullable = false)
    private RoleEntity roleEntity;
}
