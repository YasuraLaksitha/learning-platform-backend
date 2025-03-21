package edu.opl.backend.controller;

import edu.opl.backend.dto.Instructor;
import edu.opl.backend.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
@CrossOrigin
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping("/register")
    ResponseEntity<Instructor> persist(@RequestBody Instructor instructor) {
        return ResponseEntity.status(201).body(instructorService.create(instructor));
    }

    @GetMapping("/byId/{id}")
    Instructor findById(@PathVariable("id") Long id) {
        return instructorService.findById(id);
    }

    @GetMapping("/instructors")
    List<Instructor> findAll() {
        return instructorService.findAll();
    }

    @PutMapping("/update")
    ResponseEntity<Instructor> replace(@RequestBody Instructor instructor) {
        return ResponseEntity.status(HttpStatus.OK).body(instructorService.update(instructor));
    }

    @DeleteMapping("/delete")
    HttpStatus remove(@RequestBody Instructor instructor) {
        instructorService.delete(instructor);
        return HttpStatus.OK;
    }

    @PatchMapping("/isActive/{id}/{isActive}")
    HttpStatus setActive(@PathVariable("id") Long id, @PathVariable("isActive") boolean isActive) {
        instructorService.setActive(id,isActive);
        return HttpStatus.OK;
    }
}
