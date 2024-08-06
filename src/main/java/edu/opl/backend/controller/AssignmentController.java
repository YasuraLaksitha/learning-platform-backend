package edu.opl.backend.controller;

import edu.opl.backend.dto.Assignment;
import edu.opl.backend.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Assignment> persist(@RequestBody Assignment assignment) {
        return ResponseEntity.status(201).body(assignmentService.create(assignment));
    }

    @GetMapping("/byId/{id}")
    Assignment findById(@PathVariable UUID id) {
        return assignmentService.findById(id);
    }

    @GetMapping("/assignments")
    List<Assignment> findAll() {
        return assignmentService.findAll();
    }

    @PutMapping("/update")
    ResponseEntity<Assignment> replace(@RequestBody Assignment assignment) {
        return ResponseEntity.status(HttpStatus.OK).body(assignmentService.update(assignment));
    }

    @DeleteMapping("/delete")
    HttpStatus remove(@RequestBody Assignment assignment) {
        assignmentService.delete(assignment);
        return HttpStatus.OK;
    }
}
