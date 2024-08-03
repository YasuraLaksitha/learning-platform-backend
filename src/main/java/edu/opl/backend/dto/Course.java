package edu.opl.backend.dto;

import lombok.Data;

@Data
public class Course {
    private Integer id;
    private String title;
    private String description;
    private Instructor instructor;
}
