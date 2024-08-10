package edu.opl.backend.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admin")
@AttributeOverride(name = "id",column = @Column(name = "admin_id"))
@EqualsAndHashCode(callSuper = true)
public non-sealed class AdminEntity extends PersonEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_type", nullable = false)
    private RoleEntity roleEntity;
}
