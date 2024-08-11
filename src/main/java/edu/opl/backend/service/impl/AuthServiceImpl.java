package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.dto.*;
import edu.opl.backend.entity.AdminEntity;
import edu.opl.backend.entity.InstructorEntity;
import edu.opl.backend.entity.PersonDetailsEntity;
import edu.opl.backend.entity.StudentEntity;
import edu.opl.backend.exception.EntityNotFoundException;
import edu.opl.backend.repository.AdminRepository;
import edu.opl.backend.repository.InstructorRepository;
import edu.opl.backend.repository.PersonDetailsRepository;
import edu.opl.backend.repository.StudentRepository;
import edu.opl.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PersonDetailsRepository repository;

    private final AdminRepository adminRepository;

    private final InstructorRepository instructorRepository;

    private final StudentRepository studentRepository;

    private final ObjectMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final PersonDetailsEntity personDetailsEntity = repository.findByUsername(username);
        if (personDetailsEntity == null) throw new UsernameNotFoundException(username);
        final PersonDetails personDetails = mapper.convertValue(personDetailsEntity, PersonDetails.class);
        return new User(username, personDetails.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(personDetails.getRole().toString())));
    }

    @Override
    public <T extends Person> T getCurrentPerson(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return getPerson(username);
    }

    @SuppressWarnings("unchecked")
    private <T extends Person> T getPerson(String username) {
        AdminEntity adminEntity = adminRepository.findByUsername(username);
        if (adminEntity != null)
            return (T) mapper.convertValue(adminEntity, Admin.class);
        StudentEntity studentEntity = studentRepository.findByUsername(username);
        if (studentEntity != null)
            return (T) mapper.convertValue(studentEntity, Student.class);
        InstructorEntity instructorEntity = instructorRepository.findByUsername(username);
        if (instructorEntity != null)
            return (T) mapper.convertValue(instructorEntity, Instructor.class);
        throw new EntityNotFoundException(String.format("User %s not found", username));
    }
}
