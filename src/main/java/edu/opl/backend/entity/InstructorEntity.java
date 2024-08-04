package edu.opl.backend.entity;

import edu.opl.backend.util.State;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "instructor")
@AttributeOverride(name = "id",column = @Column(name = "instructor_id"))
public non-sealed class InstructorEntity extends PersonEntity {
    private String bio;
    private String specialization;
    private String experience;
    @Enumerated(value = EnumType.STRING)
    private State status;
    private boolean isActive;

    @OneToMany(mappedBy = "instructorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseEntity> courseEntitySet;
}
