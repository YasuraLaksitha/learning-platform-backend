package edu.opl.backend.controller;

import edu.opl.backend.dto.Student;
import edu.opl.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        final Student savedStudent = studentService.create(student);
        return ResponseEntity.status(201).body(savedStudent);
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
    ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.OK).body( studentService.update(student));
    }

    @DeleteMapping("/delete")
    HttpStatus deleteStudent(@RequestBody Student student) {
        studentService.delete(student);
        return HttpStatus.OK;
    }
}
