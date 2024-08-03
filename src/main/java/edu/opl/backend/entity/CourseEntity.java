package edu.opl.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_Id")
    private InstructorEntity instructorEntity;
}
