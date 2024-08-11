package edu.opl.backend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonDetails {
    private Long userId;
    private String username;
    private String password;
    private Role role;
}
