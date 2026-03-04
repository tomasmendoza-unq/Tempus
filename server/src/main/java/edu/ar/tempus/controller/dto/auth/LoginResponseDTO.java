package edu.ar.tempus.controller.dto.auth;

public record LoginResponseDTO(
        String token,
        String tipo,
        Long id,
        String email,
        String role
) {
}