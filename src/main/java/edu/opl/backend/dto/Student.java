package edu.opl.backend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper=true)
@Data
public class Student extends Person{
    private LocalDate dob;
    private String gender;
    private Set<Course> courseSet;
}
