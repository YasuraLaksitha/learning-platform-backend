package edu.opl.backend.controller;

import edu.opl.backend.dto.Course;
import edu.opl.backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/register")
    ResponseEntity<Course> persist(@RequestBody Course course) {
        return ResponseEntity.status(201).body(courseService.create(course));
    }

    @GetMapping("/byId/{id}")
    Course findById(@PathVariable UUID id) {
        return courseService.findById(id);
    }

    @GetMapping("/courses")
    List<Course> findAll() {
        return courseService.findAll();
    }

    @PutMapping("/update")
    ResponseEntity<Course> replace(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.update(course));
    }

    @DeleteMapping("/delete")
    HttpStatus delete(@RequestBody Course course) {
        courseService.delete(course);
        return HttpStatus.OK;
    }
}
