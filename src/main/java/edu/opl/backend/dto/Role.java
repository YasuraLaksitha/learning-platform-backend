package edu.opl.backend.dto;

import edu.opl.backend.util.RoleType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Role {
    private UUID roleId;
    private RoleType roleType;
    private String roleDescription;
}
