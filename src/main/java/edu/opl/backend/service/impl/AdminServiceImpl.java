package edu.opl.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.opl.backend.dto.Admin;
import edu.opl.backend.entity.AdminEntity;
import edu.opl.backend.repository.AdminRepository;
import edu.opl.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repository;

    private final ObjectMapper mapper;

    @Override
    public Admin create(final Admin admin) {
        final AdminEntity adminEntity = repository.save(mapper.convertValue(admin, AdminEntity.class));
        return mapper.convertValue(adminEntity, Admin.class);
    }

    @Override
    public Admin findById(final UUID uuid) {
        final Optional<AdminEntity> adminEntity = repository.findById(uuid);
        return adminEntity.map(entity -> mapper.convertValue(entity, Admin.class)).orElse(null);
    }

    @Override
    public List<Admin> findAll() {
        return repository.findAll().stream()
                .map(adminEntity -> mapper.convertValue(adminEntity, Admin.class)).toList();
    }

    @Override
    public void update(Admin admin) {
        this.create(admin);
    }

    @Override
    public void delete(Admin admin) {
        repository.deleteById(admin.getId());
    }
}
