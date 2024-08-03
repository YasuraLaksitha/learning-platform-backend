package edu.opl.backend.repository;

import edu.opl.backend.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
}
