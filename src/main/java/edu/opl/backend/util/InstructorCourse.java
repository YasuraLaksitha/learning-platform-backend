package edu.opl.backend.util;

import edu.opl.backend.dto.Course;
import edu.opl.backend.dto.Instructor;
import lombok.Data;

@Data
public class InstructorCourse {
    private Instructor instructor;
    private Course course;
}
