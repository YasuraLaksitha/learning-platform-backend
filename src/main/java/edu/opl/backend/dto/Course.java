package edu.opl.backend.dto;

import lombok.Data;

import java.util.Set;

@Data
public class Course {
    private Integer id;
    private String title;
    private String description;
    private Instructor instructor;
    private Set<Student> studentSet;
}
