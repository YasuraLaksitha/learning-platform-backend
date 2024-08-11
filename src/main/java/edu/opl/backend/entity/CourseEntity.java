package edu.opl.backend.entity;

import edu.opl.backend.util.State;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private State status;

    private String rejectedReason;

    @ManyToOne
    @JoinColumn(name = "instructor_Id", foreignKey = @ForeignKey(name = "FKInstructorId"))
    private InstructorEntity instructorEntity;

    @ManyToMany(
            mappedBy = "courseEntitySet",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            targetEntity = StudentEntity.class
    )
    private Set<StudentEntity> studentEntitySet;

    @OneToMany(mappedBy = "courseEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AssignmentEntity> assignmentEntitySet;

    @ManyToOne
    @JoinColumn(name = "role_type", nullable = false)
    private RoleEntity roleEntity;
}
