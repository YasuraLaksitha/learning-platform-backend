package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.config.PersonValidator;
import edu.opl.backend.dto.Student;
import edu.opl.backend.entity.StudentEntity;
import edu.opl.backend.exception.EmptyValuePassedException;
import edu.opl.backend.exception.EntityNotFoundException;
import edu.opl.backend.repository.StudentRepository;
import edu.opl.backend.service.StudentService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository repository;

    private final ObjectMapper mapper;

    private final PersonValidator personValidator;


    @Override
    public Student create(final Student student) {
        personValidator.isValidPerson(student, true);
        if (Boolean.TRUE.equals(repository.existsByEmail(student.getEmail())))
            throw new EntityExistsException("Student already exists");
        StudentEntity studentEntity = repository.save(mapper.convertValue(student, StudentEntity.class));
        return mapper.convertValue(studentEntity, Student.class);
    }

    @Override
    public Student findById(final UUID value) {
        if (!StringUtils.hasText(value.toString()))
            throw new EmptyValuePassedException("Student id is not provided");
        Optional<StudentEntity> studentEntity = repository.findById(value);
        if (studentEntity.isEmpty())
            throw new EntityNotFoundException(String.format("Student with id %s not found", value));
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
    public Student update(final Student student) {
        personValidator.isValidPerson(student, false);
        if (!repository.existsById(student.getId()))
            throw new EntityNotFoundException(String.format("Student with id %s not found", student.getId()));
        return mapper.convertValue(repository
                .save(mapper.convertValue(student, StudentEntity.class)), Student.class);
    }

    @Override
    public void delete(final Student student) {
        if (repository.existsById(student.getId())) {
            repository.delete(mapper.convertValue(student, StudentEntity.class));
            return;
        }
        throw new EntityNotFoundException(String.format("Student with id %s doesn't exist", student.getId()));
    }


}
