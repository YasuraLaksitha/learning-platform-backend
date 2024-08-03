package edu.opl.backend.controller;

import edu.opl.backend.dto.Student;
import edu.opl.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/student")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/register")
    void saveStudent(@RequestBody Student student) {
        studentService.create(student);
    }

    @GetMapping("/byId/{id}")
    Student getStudentById(@PathVariable("id") UUID id) {
        return studentService.findById(id);
    }

    @GetMapping("/students")
    List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @PutMapping("/update")
    void updateStudent(@RequestBody Student student) {
        studentService.update(student);
    }

    @DeleteMapping("/delete")
    void deleteStudent(@RequestBody Student student) {
        studentService.delete(student);
    }
}
