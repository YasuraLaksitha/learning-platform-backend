package edu.opl.backend.service;

import edu.opl.backend.dto.Role;
import edu.opl.backend.util.RoleType;

import java.util.UUID;

public non-sealed interface RoleService extends CommonService<Role, UUID> {
    Role retriveByRoleType(RoleType roleType);
}
