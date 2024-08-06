package edu.opl.backend.repository;

import edu.opl.backend.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {
    Boolean existsByEmail(String email);
}
