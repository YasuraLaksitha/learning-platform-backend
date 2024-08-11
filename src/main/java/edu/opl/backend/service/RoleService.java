package edu.opl.backend.service;

import edu.opl.backend.dto.Role;
import edu.opl.backend.util.RoleType;

public non-sealed interface RoleService extends CommonService<Role, Long> {
    Role retrieveByRoleType(RoleType roleType);
}
