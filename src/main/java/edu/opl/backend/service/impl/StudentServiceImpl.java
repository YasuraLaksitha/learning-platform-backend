package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.dto.Student;
import edu.opl.backend.entity.StudentEntity;
import edu.opl.backend.repository.StudentRepository;
import edu.opl.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    private final ObjectMapper mapper;


    @Override
    public Student create(final Student student) {
        StudentEntity studentEntity = repository.save(mapper.convertValue(student, StudentEntity.class));
        return mapper.convertValue(studentEntity, Student.class);
    }

    @Override
    public Student findById(UUID value) {
        StudentEntity studentEntity = repository.findById(value).orElse(null);
        return mapper.convertValue(studentEntity, Student.class);
    }

    @Override
    public List<Student> findAll() {
        List<StudentEntity> studentEntitySet = repository.findAll();
        if (!studentEntitySet.isEmpty())
            return studentEntitySet.stream().map(
                    studentEntity -> mapper.convertValue(studentEntity, Student.class)
            ).toList();
        return Collections.emptyList();
    }

    @Override
    public void update(final Student student) {
        this.create(student);
    }

    @Override
    public void delete(final Student student) {
        repository.delete(mapper.convertValue(student, StudentEntity.class));
    }
}
