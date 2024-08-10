package edu.opl.backend.service;

import edu.opl.backend.dto.Person;

public interface PersonDetailsService {
    void register(Person person);
    Person login(String email, String password);
}
