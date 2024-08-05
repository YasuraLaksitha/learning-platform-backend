package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.config.PersonValidator;
import edu.opl.backend.dto.Instructor;
import edu.opl.backend.entity.InstructorEntity;
import edu.opl.backend.exception.EmptyValuePassedException;
import edu.opl.backend.exception.EntityNotFoundException;
import edu.opl.backend.repository.InstructorRepository;
import edu.opl.backend.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    public static final String NOT_FOUND = "Instructor with ID %s not found";
    private final InstructorRepository repository;

    private final ObjectMapper mapper;

    private final PersonValidator personValidator;

    @Override
    public Instructor create(Instructor instructor) {
        personValidator.isValidPerson(instructor,true);
        final InstructorEntity instructorEntity = repository.save(mapper.convertValue(instructor, InstructorEntity.class));
        return mapper.convertValue(instructorEntity, Instructor.class);
    }

    @Override
    public Instructor findById(final UUID uuid) {
        if (!StringUtils.hasText(uuid.toString()))
            throw new EmptyValuePassedException("ID is not provided");
        final Optional<InstructorEntity> entity = repository.findById(uuid);
        if (entity.isEmpty())
            throw new EntityNotFoundException(String.format(NOT_FOUND, uuid));
        return mapper.convertValue(entity, Instructor.class);
    }

    @Override
    public List<Instructor> findAll() {
        return repository.findAll().stream()
                .map(instructorEntity -> mapper.convertValue(instructorEntity, Instructor.class)).toList();
    }

    @Override
    public Instructor update(final Instructor instructor) {
        personValidator.isValidPerson(instructor,false);
        if(!repository.existsById(instructor.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, instructor.getId()));
        return mapper.convertValue(repository.save(mapper.convertValue(this.create(instructor),
                InstructorEntity.class)), Instructor.class);
    }

    @Override
    public void delete(Instructor instructor) {
        personValidator.isValidPerson(instructor,false);
        if (!repository.existsById(instructor.getId()))
            throw new EntityNotFoundException(String.format(NOT_FOUND, instructor.getId()));
        repository.deleteById(instructor.getId());
    }

    @Override
    public void setActive(UUID id, boolean isActive) {
        if (!StringUtils.hasText(id.toString()))
            throw new EmptyValuePassedException("ID isn't provided");
        final Optional<InstructorEntity> entity = repository.findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException(String.format("Instructor with id %s not found", id));
        else if (entity.get().isActive() && isActive)
            throw new IllegalArgumentException("The Instructor is already activated");
        else if (!entity.get().isActive() && !isActive) {
            throw new IllegalArgumentException("The Instructor is already deactivated");
        }
        entity.get().setActive(isActive);
        repository.save(entity.get());
    }
}
