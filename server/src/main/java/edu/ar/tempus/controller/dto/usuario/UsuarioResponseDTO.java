package edu.ar.tempus.controller.dto.usuario;

import edu.ar.tempus.model.Role;
import edu.ar.tempus.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String email,
        String nombre,
        String apellido,
        String telefono,
        Role role,
        boolean enabled
){
    public static UsuarioResponseDTO desdeModelo(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getRole(),
                usuario.isEnabled()

        );
    }
}