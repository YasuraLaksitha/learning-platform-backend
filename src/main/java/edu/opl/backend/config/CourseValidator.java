package edu.opl.backend.config;

import edu.opl.backend.dto.Course;
import edu.opl.backend.exception.EmptyValuePassedException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CourseValidator {

    public void isValidCourse(Course course, Boolean excludeId) {
        if (course == null)
            throw new EmptyValuePassedException("Course is empty");
        else if (Boolean.FALSE.equals(excludeId))
            checkId(course.getId());
        else if (!StringUtils.hasText(course.getTitle()))
            throw new EmptyValuePassedException("Course title is empty");
        else if (!StringUtils.hasText(course.getDescription()))
            throw new EmptyValuePassedException("Course description is empty");
    }

    private void checkId(Long id) {
        if (!StringUtils.hasText(id.toString()))
            throw new EmptyValuePassedException("Course id is empty");
    }
}
