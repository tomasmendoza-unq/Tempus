package com.ecovida.ecommerce_backend.controller.dto.usuario;

import com.ecovida.ecommerce_backend.models.Role;
import com.ecovida.ecommerce_backend.models.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String email,
        String nombre,
        String apellido,
        String provincia,
        String localidad,
        String codigoPostal,
        String domicilio,
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
                usuario.getProvincia(),
                usuario.getLocalidad(),
                usuario.getCodigoPostal(),
                usuario.getDomicilio(),
                usuario.getTelefono(),
                usuario.getRole(),
                usuario.isEnabled()

        );
    }
}