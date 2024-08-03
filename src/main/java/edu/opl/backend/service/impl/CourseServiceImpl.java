package edu.opl.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.dto.Course;
import edu.opl.backend.entity.CourseEntity;
import edu.opl.backend.repository.CourseRepository;
import edu.opl.backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    private final ObjectMapper mapper;

    @Override
    public Course create(Course course) {
        CourseEntity courseEntity = repository.save(mapper.convertValue(course, CourseEntity.class));
        return mapper.convertValue(courseEntity, Course.class);
    }

    @Override
    public Course findById(final UUID uuid) {
        Optional<CourseEntity> entity = repository.findById(uuid);
        return entity.map(courseEntity -> mapper.convertValue(courseEntity, Course.class)).orElse(null);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll().stream()
                .map(courseEntity -> mapper.convertValue(courseEntity, Course.class)).toList();
    }

    @Override
    public void update(final Course course) {
        this.create(course);
    }

    @Override
    public void delete(final Course course) {
        repository.delete(mapper.convertValue(course, CourseEntity.class));
    }
}
