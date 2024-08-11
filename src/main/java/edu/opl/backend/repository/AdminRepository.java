package edu.opl.backend.repository;

import edu.opl.backend.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Boolean existsByEmail(String email);

    AdminEntity findByUsername(String username);
}
