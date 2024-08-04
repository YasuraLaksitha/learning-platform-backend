package edu.opl.backend.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponseBody {
    private String message;
    private HttpStatus status;
}
