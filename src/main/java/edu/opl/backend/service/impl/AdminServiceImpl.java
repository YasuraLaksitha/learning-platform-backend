package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.config.CourseValidator;
import edu.opl.backend.config.PersonValidator;
import edu.opl.backend.dto.Admin;
import edu.opl.backend.dto.Course;
import edu.opl.backend.dto.Instructor;
import edu.opl.backend.entity.AdminEntity;
import edu.opl.backend.entity.CourseEntity;
import edu.opl.backend.entity.InstructorEntity;
import edu.opl.backend.exception.EmptyValuePassedException;
import edu.opl.backend.exception.EntityNotFoundException;
import edu.opl.backend.repository.AdminRepository;
import edu.opl.backend.repository.CourseRepository;
import edu.opl.backend.repository.InstructorRepository;
import edu.opl.backend.service.AdminService;
import edu.opl.backend.util.State;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    public static final String NOT_FOUND = "Instructor with id %s not found";

    public static final String COURSE_NOT_FOUND = "Course with id %s not found";

    public static final String ADMIN_NOT_FOUND = "Admin with id %s not found";

    private final AdminRepository repository;

    private final ObjectMapper mapper;

    private final PersonValidator personValidator;

    private final CourseRepository courseRepository;

    private final InstructorRepository instructorRepository;

    private final CourseValidator courseValidator;

    @Override
    public Admin create(Admin admin) {
        personValidator.isValidPerson(admin,true);
        if (Boolean.TRUE.equals(repository.existsByEmail(admin.getEmail())))
            throw new EntityExistsException("Admin already exists");
        final AdminEntity adminEntity = repository.save(mapper.convertValue(admin, AdminEntity.class));
        return mapper.convertValue(adminEntity, Admin.class);
    }

    @Override
    public Admin findById(final UUID uuid) {
        if (!StringUtils.hasText(uuid.toString()))
            throw new EmptyValuePassedException("id not provided");
        final Optional<AdminEntity> adminEntity = repository.findById(uuid);
        if (!adminEntity.isPresent())
            throw new EntityNotFoundException(String.format(ADMIN_NOT_FOUND, uuid.toString()));
        return mapper.convertValue(adminEntity.get(), Admin.class);
    }

    @Override
    public List<Admin> findAll() {
        return repository.findAll().stream()
                .map(adminEntity -> mapper.convertValue(adminEntity, Admin.class)).toList();
    }

    @Override
    public Admin update(Admin admin) {
        personValidator.isValidPerson(admin,false);
        if (!repository.existsById(admin.getId()))
            throw new EntityNotFoundException(String.format(ADMIN_NOT_FOUND, admin.getId()));
        return mapper.convertValue(repository.save(mapper.convertValue(admin, AdminEntity.class)), Admin.class);
    }

    @Override
    public void delete(final Admin admin) {
        if (!repository.existsById(admin.getId()))
            throw new EntityNotFoundException(String.format(ADMIN_NOT_FOUND, admin.getId()));
        repository.deleteById(admin.getId());
    }

    @Override
    public void approveCourse(Course course) {
        courseValidator.isValidCourse(course, false);
        if (!courseRepository.existsById(course.getId()))
            throw new EntityNotFoundException(String.format(COURSE_NOT_FOUND, course.getId()));
        else if (State.APPROVED.equals(course.getStatus()))
            throw new EntityExistsException(String.format("Course with id %s already approved", course.getId()));
        course.setStatus(State.APPROVED);
        courseRepository.save(mapper.convertValue(course, CourseEntity.class));
    }

    @Override
    public void rejectCourse(Course course) {
        courseValidator.isValidCourse(course, false);
        if (!courseRepository.existsById(course.getId()))
            throw new EntityNotFoundException(String.format(COURSE_NOT_FOUND, course.getId()));
        else if (State.REJECTED.equals(course.getStatus()))
            throw new EntityExistsException(String.format("Course with id %s already rejected", course.getId()));
        course.setStatus(State.REJECTED);
        courseRepository.save(mapper.convertValue(course, CourseEntity.class));
    }

    @Override
    public void addInstructorToCourse(Instructor instructor, Course course) {
        courseValidator.isValidCourse(course, false);
        personValidator.isValidPerson(instructor,false);
        if (!courseRepository.existsById(course.getId()))
            throw new EntityNotFoundException(String.format(COURSE_NOT_FOUND, course.getId()));
        else if (State.REJECTED.equals(course.getStatus()))
            course.setStatus(State.APPROVED);
        else if (!instructorRepository.existsById(instructor.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, instructor.getId()));
        else if (State.REJECTED.equals(instructor.getStatus()))
            instructor.setStatus(State.APPROVED);
        instructor.getCourseSet().add(course);
        instructorRepository.save(mapper.convertValue(instructor, InstructorEntity.class));
    }

    @Override
    public void removeInstructorFromCourse(Instructor instructor, Course course) {
        courseValidator.isValidCourse(course, false);
        personValidator.isValidPerson(instructor,false);
        if (!courseRepository.existsById(course.getId()))
            throw new EntityNotFoundException(String.format(COURSE_NOT_FOUND, course.getId()));
        else if (!instructorRepository.existsById(instructor.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, instructor.getId()));
        else if (!instructor.getCourseSet().contains(course))
            throw new EntityNotFoundException(String
                    .format("Course with id %s not found in instructor course list", course.getId()));
        instructor.getCourseSet().remove(course);
        instructorRepository.save(mapper.convertValue(instructor, InstructorEntity.class));
    }

    @Override
    public void approveInstructor(Instructor instructor) {
        if (!instructorRepository.existsById(instructor.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, instructor.getId()));
        else if (State.APPROVED.equals(instructor.getStatus()))
            throw new EntityExistsException(String.format("Instructor with id %s already approved", instructor.getId()));
        instructor.setStatus(State.APPROVED);
        instructorRepository.save(mapper.convertValue(instructor, InstructorEntity.class));
    }

    @Override
    public void rejectInstructor(Instructor instructor) {
        personValidator.isValidPerson(instructor,false);
        if (!instructorRepository.existsById(instructor.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, instructor.getId()));
        if(State.REJECTED.equals(instructor.getStatus()))
            throw new EntityExistsException(String.format("Instructor with id %s already rejected", instructor.getId()));
        instructor.setStatus(State.REJECTED);
        instructorRepository.save(mapper.convertValue(instructor, InstructorEntity.class));
    }
}
