package edu.opl.backend.dto;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public abstract sealed class Person
        permits Admin, Instructor, Student
{
    private UUID id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String role;
    private boolean available;
}
