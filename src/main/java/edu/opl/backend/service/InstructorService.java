package edu.opl.backend.service;

import edu.opl.backend.dto.Instructor;

public non-sealed interface InstructorService extends CommonService<Instructor, Long> {
    void setActive(Long id, boolean isActive);
}
