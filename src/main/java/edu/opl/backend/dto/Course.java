package edu.opl.backend.dto;

import edu.opl.backend.util.State;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class Course {
    private UUID id;
    private String title;
    private String description;
    private State status;
    private String rejectedReason;
    private Instructor instructor;
    private Set<Student> studentSet;
    private Set<Assignment> assignmentSet;
}
