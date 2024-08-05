package edu.opl.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.config.CourseValidator;
import edu.opl.backend.dto.Course;
import edu.opl.backend.entity.CourseEntity;
import edu.opl.backend.exception.EmptyValuePassedException;
import edu.opl.backend.exception.EntityNotFoundException;
import edu.opl.backend.repository.CourseRepository;
import edu.opl.backend.service.CourseService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    public static final String NOT_FOUND = "Course with ID %s not found";

    private final CourseRepository repository;

    private final ObjectMapper mapper;

    private final CourseValidator courseValidator;

    @Override
    public Course create(Course course) {
        courseValidator.isValidCourse(course,true);
        if (repository.existsById(course.getId()))
            throw new EntityExistsException("Entity already exists");
        CourseEntity courseEntity = repository.save(mapper.convertValue(course, CourseEntity.class));
        return mapper.convertValue(courseEntity, Course.class);
    }

    @Override
    public Course findById(final UUID uuid) {
        if (!StringUtils.hasText(uuid.toString()))
            throw new EmptyValuePassedException("ID is not provided");
        Optional<CourseEntity> entity = repository.findById(uuid);
        if (entity.isEmpty())
            throw new EntityNotFoundException(String.format(NOT_FOUND, uuid));
        return mapper.convertValue(entity, Course.class);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll().stream()
                .map(courseEntity -> mapper.convertValue(courseEntity, Course.class)).toList();
    }

    @Override
    public Course update(final Course course) {
        courseValidator.isValidCourse(course,false);
        if (!repository.existsById(course.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, course.getId()));
        return mapper.convertValue(repository.save(mapper.convertValue(course, CourseEntity.class)),Course.class);
    }

    @Override
    public void delete(final Course course) {
        courseValidator.isValidCourse(course,false);
        if (!repository.existsById(course.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, course.getId()));
        repository.delete(mapper.convertValue(course, CourseEntity.class));
    }
}
