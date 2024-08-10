package edu.opl.backend.entity;

import edu.opl.backend.dto.util.RoleType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "role_type")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15,unique=true, updatable=false)
    private RoleType roleType;

    private String roleDescription;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<PersonDetailsEntity> userDetails;
}
