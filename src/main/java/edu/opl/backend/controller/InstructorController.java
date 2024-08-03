package edu.opl.backend.controller;

import edu.opl.backend.dto.Instructor;
import edu.opl.backend.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
@CrossOrigin
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping("/register")
    void saveInstructor(@RequestBody Instructor instructor) {
        instructorService.create(instructor);
    }

    @GetMapping("/byId/{id}")
    Instructor getInstructorById(@PathVariable("id") UUID id) {
        return instructorService.findById(id);
    }

    @GetMapping("/instructors")
    List<Instructor> getAllInstructors() {
        return instructorService.findAll();
    }

    @PutMapping("/update")
    void updateInstructor(@RequestBody Instructor instructor) {
        instructorService.update(instructor);
    }

    @DeleteMapping("/delete")
    void deleteInstructor(@RequestBody Instructor instructor) {
        instructorService.delete(instructor);
    }
}
