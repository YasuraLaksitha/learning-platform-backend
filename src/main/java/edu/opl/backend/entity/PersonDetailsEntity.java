package edu.opl.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "user_details")
public class PersonDetailsEntity {
    @Id
    private UUID personId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false,unique = true)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role",foreignKey = @ForeignKey(name = "FK_RoleId"))
    private RoleEntity role;
}
