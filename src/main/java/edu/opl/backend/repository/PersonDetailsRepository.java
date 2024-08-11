package edu.opl.backend.repository;

import edu.opl.backend.entity.PersonDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonDetailsRepository extends JpaRepository<PersonDetailsEntity, UUID> {
    PersonDetailsEntity findByUsername(String username);

    Boolean existsByUsernameAndPassword(String username, String password);
}
