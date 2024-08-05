package edu.opl.backend.service;

import edu.opl.backend.dto.Admin;
import edu.opl.backend.dto.Course;
import edu.opl.backend.dto.Instructor;

import java.util.UUID;

public non-sealed interface AdminService extends CommonService<Admin, UUID>{
    void approveCourse(Course course);
    void rejectCourse(Course course);
    void addInstructorToCourse(Instructor instructor, Course course);
    void removeInstructorFromCourse(Instructor instructor, Course course);
    void approveInstructor(Instructor instructor);
    void rejectInstructor(Instructor instructor);
}
