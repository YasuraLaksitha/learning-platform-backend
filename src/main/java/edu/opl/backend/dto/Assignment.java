package edu.opl.backend.dto;


import edu.opl.backend.util.SubmitionStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Assignment {
    private Long id;
    private String title;
    private String description;
    private LocalDate allocatedDate;
    private LocalDate dueDate;
    private SubmitionStatus status;
    private Student student;
    private Course course;
    private Role role;
}
