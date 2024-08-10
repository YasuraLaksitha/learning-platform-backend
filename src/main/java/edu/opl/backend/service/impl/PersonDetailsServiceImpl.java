package edu.opl.backend.service.impl;

import edu.opl.backend.dto.Person;
import edu.opl.backend.service.PersonDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PersonDetailsServiceImpl implements PersonDetailsService {



    @Override
    public void register(Person person) {

    }

    @Override
    public Person login(String email, String password) {
        return null;
    }


}
