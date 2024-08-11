package edu.opl.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract sealed class PersonEntity
        permits ManagerEntity, AdminEntity, InstructorEntity, StudentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    @Transient
    private RoleEntity role;

    private boolean isAvailable;
}
