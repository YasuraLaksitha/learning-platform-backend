package edu.opl.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
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
    private boolean isAvailable;
}
