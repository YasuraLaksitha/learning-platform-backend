package edu.opl.backend.controller;

import edu.opl.backend.dto.Admin;
import edu.opl.backend.dto.Course;
import edu.opl.backend.dto.Instructor;
import edu.opl.backend.service.AdminService;
import edu.opl.backend.util.InstructorCourse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    ResponseEntity<Admin> persist(@RequestBody Admin admin) {
        final Admin savedAdmin = adminService.create(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }

    @GetMapping("/admins")
    List<Admin> retrieveAll() {
        return adminService.findAll();
    }

    @GetMapping("/byId/{id}")
    Admin retrieveById(@PathVariable UUID id) {
        return adminService.findById(id);
    }

    @PutMapping("/update")
    ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        final Admin updated = adminService.update(admin);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/delete")
    HttpStatus remove(@RequestBody Admin admin) {
        adminService.delete(admin);
        return HttpStatus.OK;
    }

    @PatchMapping("/approve-course")
    HttpStatus approveCourse(@RequestBody Course course) {
        adminService.approveCourse(course);
        return HttpStatus.OK;
    }

    @PatchMapping("/reject-course")
    HttpStatus rejectCourse(@RequestBody Course course) {
        adminService.rejectCourse(course);
        return HttpStatus.OK;
    }

    @PatchMapping("/approve-instructor")
    HttpStatus approveInstructor(@RequestBody Instructor instructor) {
        adminService.approveInstructor(instructor);
        return HttpStatus.OK;
    }

    @PatchMapping("/reject-instructor")
    HttpStatus rejectInstructor(@RequestBody Instructor instructor) {
        adminService.rejectInstructor(instructor);
        return HttpStatus.OK;
    }

    @PatchMapping("/assign-course")
    HttpStatus assignCourse(@RequestBody InstructorCourse instructorCourse) {
        adminService.addInstructorToCourse(instructorCourse.getInstructor(), instructorCourse.getCourse());
        return HttpStatus.OK;
    }

    @PatchMapping("/unassign-course")
    HttpStatus unAssignCourse(@RequestBody InstructorCourse instructorCourse) {
        adminService.removeInstructorFromCourse(instructorCourse.getInstructor(), instructorCourse.getCourse());
        return HttpStatus.OK;
    }
}
