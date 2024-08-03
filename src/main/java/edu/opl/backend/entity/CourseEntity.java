package edu.opl.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

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

    @ManyToMany(mappedBy = "courseEntitySet", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<StudentEntity> studentEntitySet;
}
