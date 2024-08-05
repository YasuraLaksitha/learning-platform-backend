package edu.opl.backend.entity;

import edu.opl.backend.util.SubmitionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "assignemnt")
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "assignment_id")
    private UUID  id;
    private String title;
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
}
