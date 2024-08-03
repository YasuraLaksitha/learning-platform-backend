package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.dto.Instructor;
import edu.opl.backend.entity.InstructorEntity;
import edu.opl.backend.repository.InstructorRepository;
import edu.opl.backend.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository repository;

    private final ObjectMapper mapper;

    @Override
    public Instructor create(Instructor instructor) {
        final InstructorEntity instructorEntity = repository.save(mapper.convertValue(instructor, InstructorEntity.class));
        return mapper.convertValue(instructorEntity, Instructor.class);
    }

    @Override
    public Instructor findById(final UUID uuid) {
        final Optional<InstructorEntity> entity = repository.findById(uuid);
        return entity.map(instructorEntity -> mapper.convertValue(instructorEntity, Instructor.class)).orElse(null);
    }

    @Override
    public List<Instructor> findAll() {
        return repository.findAll().stream()
                .map(instructorEntity -> mapper.convertValue(instructorEntity, Instructor.class)).toList();
    }

    @Override
    public void update(final Instructor instructor) {
        this.create(instructor);
    }

    @Override
    public void delete(Instructor instructor) {
        repository.deleteById(instructor.getId());
    }
}
