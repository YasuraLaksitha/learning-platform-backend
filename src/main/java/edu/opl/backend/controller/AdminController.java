package edu.opl.backend.controller;

import edu.opl.backend.dto.Admin;
import edu.opl.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
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
    void saveAdmin(@RequestBody Admin admin) {
        adminService.create(admin);
    }

    @GetMapping("/admins")
    List<Admin> getAllAdmins() {
        return adminService.findAll();
    }

    @GetMapping("/byId/{id}")
    Admin getAdminById(@PathVariable UUID id) {
        return adminService.findById(id);
    }

    @PutMapping("/update")
    void updateAdmin(@RequestBody Admin admin) {
        adminService.update(admin);
    }

    @DeleteMapping("/delete")
    void deleteAdmin(@RequestBody Admin admin) {
        adminService.delete(admin);
    }
}
