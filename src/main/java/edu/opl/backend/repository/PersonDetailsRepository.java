package edu.opl.backend.repository;

import edu.opl.backend.entity.PersonDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDetailsRepository extends JpaRepository<PersonDetailsEntity, Long> {
    PersonDetailsEntity findByUsername(String username);

    Boolean existsByUsernameAndPassword(String username, String password);
}
