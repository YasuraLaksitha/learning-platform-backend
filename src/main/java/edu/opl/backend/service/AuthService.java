package edu.opl.backend.service;

import edu.opl.backend.dto.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    <T extends Person> T getCurrentPerson(UserDetails userDetails);
}
