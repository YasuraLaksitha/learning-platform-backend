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

    @Column(nullable = false)
    private String specialization;

    private String experience;

    @Enumerated(value = EnumType.STRING)
    private State status;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "instructorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseEntity> courseEntitySet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_type", nullable = false)
    private RoleEntity roleEntity;
}
