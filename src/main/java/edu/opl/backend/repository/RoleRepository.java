package edu.opl.backend.repository;

import edu.opl.backend.entity.RoleEntity;
import edu.opl.backend.util.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleType(RoleType roleType);
}
