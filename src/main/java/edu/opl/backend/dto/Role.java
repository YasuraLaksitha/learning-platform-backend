package edu.opl.backend.dto;

import edu.opl.backend.util.RoleType;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Role {
    private Long roleId;
    private RoleType roleType;
    private String roleDescription;
}
