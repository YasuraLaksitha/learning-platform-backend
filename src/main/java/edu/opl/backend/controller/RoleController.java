package edu.opl.backend.controller;

import edu.opl.backend.dto.Role;
import edu.opl.backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role")
@CrossOrigin
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/register")
    ResponseEntity<Role> persist(@RequestBody Role role) {
        final Role createdRole = roleService.create(role);
        return ResponseEntity.status(201).body(createdRole);
    }

    @GetMapping("/byType")
    Role findByType(@RequestParam Role role) {
        return roleService.retriveByRoleType(role.getRoleType());
    }

    @GetMapping("/roles")
    List<Role> findAll() {
        return roleService.findAll();
    }

    @PutMapping("/replace")
    ResponseEntity<Role> replace(@RequestBody Role role) {
        final Role updatedRole = roleService.update(role);
        return ResponseEntity.ok(updatedRole);
    }

    @PatchMapping("/update")
    ResponseEntity<Role> update(@RequestBody Role role) {
        final Role updatedRole = roleService.update(role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/remove")
    HttpStatus remove(@RequestBody Role role) {
        roleService.delete(role);
        return HttpStatus.OK;
    }
}
