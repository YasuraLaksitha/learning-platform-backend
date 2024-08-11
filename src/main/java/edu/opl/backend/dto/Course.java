package edu.opl.backend.dto;

import edu.opl.backend.util.State;
import lombok.Data;

import java.util.Set;

@Data
public class Course {
    private Long id;
    private String title;
    private String description;
    private State status;
    private Role role;
    private String rejectedReason;
    private Instructor instructor;
    private Set<Student> studentSet;
    private Set<Assignment> assignmentSet;
}
