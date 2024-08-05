package edu.opl.backend.service;

import edu.opl.backend.dto.Instructor;
import java.util.UUID;

public non-sealed interface InstructorService extends CommonService<Instructor, UUID> {

    void setActive(UUID id, boolean isActive);
}
