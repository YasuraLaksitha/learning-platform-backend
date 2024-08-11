package edu.opl.backend.controller;

import edu.opl.backend.dto.*;
import edu.opl.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<? extends Person> login(@RequestBody PersonDetails personDetails) {
        final UserDetails userDetails = authService.loadUserByUsername(personDetails.getUsername());
        final Person currentPerson = authService.getCurrentPerson(userDetails);
        return switch (currentPerson) {
            case Admin admin -> ResponseEntity.ok(admin);
            case Student student -> ResponseEntity.ok(student);
            case Instructor instructor -> ResponseEntity.ok(instructor);
            case Manager manager -> ResponseEntity.ok(manager);
            case null -> ResponseEntity.notFound().build();
        };
    }


}
