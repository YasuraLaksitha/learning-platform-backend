package edu.opl.backend.dto;

import edu.opl.backend.util.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper=true)
@Data
public non-sealed class Student extends Person {
    private LocalDate dob;
    private Gender gender;
    private Role role;
    private Set<Course> courseSet;
    private Set<Assignment> assignmentSet;
}
