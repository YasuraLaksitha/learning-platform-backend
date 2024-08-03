package edu.opl.backend.controller;

import edu.opl.backend.dto.Assignment;
import edu.opl.backend.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/create")
    void saveAssignment(@RequestBody Assignment assignment) {
        assignmentService.create(assignment);
    }

    @GetMapping("/byId/{id}")
    Assignment getAssignmentById(@PathVariable UUID id) {
        return assignmentService.findById(id);
    }

    @GetMapping("/assignments")
    List<Assignment> getAllAssignments() {
        return assignmentService.findAll();
    }

    @PutMapping("/update")
    void updateAssignment(@RequestBody Assignment assignment) {
        assignmentService.update(assignment);
    }

    @DeleteMapping("/delete")
    void deleteAssignment(@RequestBody Assignment assignment) {
        assignmentService.delete(assignment);
    }
}
