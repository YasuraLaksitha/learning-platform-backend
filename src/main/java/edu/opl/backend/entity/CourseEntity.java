package edu.opl.backend.entity;

import edu.opl.backend.util.State;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "course_id")
    private UUID id;
    private String title;
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
}
