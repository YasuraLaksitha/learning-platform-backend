package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.dto.Role;
import edu.opl.backend.entity.RoleEntity;
import edu.opl.backend.exception.EmptyValuePassedException;
import edu.opl.backend.exception.IllegalValuePassedException;
import edu.opl.backend.repository.RoleRepository;
import edu.opl.backend.service.RoleService;
import edu.opl.backend.util.RoleType;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_IS_NULL = "Role is null";

    public static final String NOT_FOUND = "Role not found";

    private final RoleRepository roleRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Role create(Role role) {
        Objects.requireNonNull(role, () -> {
            throw new EmptyValuePassedException(ROLE_IS_NULL);
        });
        return objectMapper
                .convertValue(roleRepository.save(objectMapper.convertValue(role, RoleEntity.class)), Role.class);
    }

    @Override
    public Role findById(Long Long) {
        Objects.requireNonNull(Long, () -> {
            throw new IllegalValuePassedException("Long is null");
        });
        return objectMapper.convertValue(roleRepository.findById(Long), Role.class);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll().stream()
                .map(roleEntity -> objectMapper.convertValue(roleEntity, Role.class)).toList();
    }

    @Override
    public Role update(Role role) {
        Objects.requireNonNull(role, () -> {
            throw new EmptyValuePassedException(ROLE_IS_NULL);
        });
        if (roleRepository.existsById(role.getRoleId())) {
            return objectMapper.convertValue(roleRepository
                    .save(objectMapper.convertValue(role, RoleEntity.class)), Role.class);
        }
        throw new EntityExistsException(NOT_FOUND);
    }

    @Override
    public void delete(Role role) {
        Objects.requireNonNull(role, () -> {
            throw new EmptyValuePassedException(ROLE_IS_NULL);
        });
        if (roleRepository.existsById(role.getRoleId())) {
            roleRepository.deleteById(role.getRoleId());
            return;
        }
        throw new EntityExistsException(NOT_FOUND);
    }

    @Override
    public Role retrieveByRoleType(RoleType roleType) {
        Objects.requireNonNull(roleType, () -> {
            throw new EmptyValuePassedException("RoleType is null");
        });
        final Optional<RoleEntity> roleEntity = roleRepository.findByRoleType(roleType);
        if (roleEntity.isEmpty())
            throw new EntityExistsException(NOT_FOUND);
        return objectMapper.convertValue(roleEntity, Role.class);
    }
}
