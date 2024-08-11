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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_IS_NULL = "Role is null";

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
    public Role findById(UUID uuid) {
        Objects.requireNonNull(uuid, () -> {
            throw new IllegalValuePassedException("UUID is null");
        });
        return objectMapper.convertValue(roleRepository.findById(uuid), Role.class);
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
        throw new EntityExistsException("Role not found");
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
        throw new EntityExistsException("Role not found");
    }

    @Override
    public Role retriveByRoleType(RoleType roleType) {
        Objects.requireNonNull(roleType, () -> {
            throw new EmptyValuePassedException("RoleType is null");
        });
        final Optional<RoleEntity> roleEntity = roleRepository.findByRoleType(roleType);
        if (roleEntity.isEmpty())
            throw new EntityExistsException("Role not found");
        return objectMapper.convertValue(roleEntity, Role.class);
    }
}
