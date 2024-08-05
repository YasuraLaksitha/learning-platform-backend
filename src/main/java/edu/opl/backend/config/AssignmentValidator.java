package edu.opl.backend.config;

import edu.opl.backend.dto.Assignment;
import edu.opl.backend.exception.EmptyValuePassedException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AssignmentValidator {
    public void isValidAssignment(Assignment assignment, Boolean excludeId) {
        if (assignment == null)
            throw new EmptyValuePassedException("Assignment is empty");
        else if (Boolean.FALSE.equals(excludeId) && !StringUtils.hasText(assignment.getId().toString()))
            throw new EmptyValuePassedException("Assignment id is empty");
        else if (!StringUtils.hasText(assignment.getTitle()))
            throw new EmptyValuePassedException("Assignment title is empty");
        else if (!StringUtils.hasText(assignment.getDescription()))
            throw new EmptyValuePassedException("Assignment description is empty");
    }
}
