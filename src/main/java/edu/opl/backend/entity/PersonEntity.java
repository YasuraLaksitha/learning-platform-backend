package edu.opl.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data
abstract sealed class PersonEntity
    permits AdminEntity,InstructorEntity,StudentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
    private boolean status;
}
