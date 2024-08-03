package edu.opl.backend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class Instructor extends Person {
    private String bio;
    private String specialization;
    private Set<Course> courseSet;
    private String experience;
    private boolean isAvailable;
}
