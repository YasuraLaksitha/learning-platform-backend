package edu.opl.backend.dto;

import edu.opl.backend.util.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public non-sealed class Instructor extends Person {
    private String bio;
    private String specialization;
    private Set<Course> courseSet;
    private String experience;
    private State status;
    private boolean isActive;
}
