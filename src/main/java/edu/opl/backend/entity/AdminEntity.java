package edu.opl.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
@AttributeOverride(name = "id",column = @Column(name = "admin_id"))
public non-sealed class AdminEntity extends PersonEntity {
}
