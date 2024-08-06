package edu.opl.backend.service;

import edu.opl.backend.dto.Student;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public non-sealed interface StudentService extends CommonService<Student, UUID>, UserDetailsService {
}
