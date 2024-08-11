package edu.opl.backend.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "manager")
@AttributeOverride(name = "id", column = @Column(name = "manager_id"))
@EqualsAndHashCode(callSuper = true)
public non-sealed class ManagerEntity extends PersonEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role", nullable = false, updatable = false)
    RoleEntity roleEntity;
}
