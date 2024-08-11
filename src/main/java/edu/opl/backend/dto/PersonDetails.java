package edu.opl.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class PersonDetails {
    private UUID userId;
    private String username;
    private String password;
    private Role role;
}
