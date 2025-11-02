package org.acme.entity.dto;

import java.time.Instant;

public record ErrorResponseDTO(Integer status, String error, String message, Instant timestamp) {
    public ErrorResponseDTO(Integer status, String error, String message) {
        this(status, error, message, Instant.now());
    }
}
