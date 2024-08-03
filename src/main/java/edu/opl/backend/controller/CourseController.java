package edu.opl.backend.controller;

import edu.opl.backend.dto.Course;
import edu.opl.backend.service.CourseService;
import lombok.RequiredArgsConstructor;
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
    void saveCourse(@RequestBody Course course) {
        courseService.create(course);
    }

    @GetMapping("/byId/{id}")
    Course getCourseById(@PathVariable UUID id) {
        return courseService.findById(id);
    }

    @GetMapping("/courses")
    List<Course> getAllCourses() {
        return courseService.findAll();
    }

    @PutMapping("/update")
    void updateCourse(@RequestBody Course course) {
        courseService.update(course);
    }

    @DeleteMapping("/delete")
    void deleteCourse(@RequestBody Course course) {
        courseService.delete(course);
    }
}
