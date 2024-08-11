package edu.opl.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract sealed class Person
        permits Admin, Instructor, Manager, Student
{
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private Role role;
    private boolean isAvailable;
}
