package edu.opl.backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
abstract class Person {
    private UUID id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String role;
    private boolean status;
}
