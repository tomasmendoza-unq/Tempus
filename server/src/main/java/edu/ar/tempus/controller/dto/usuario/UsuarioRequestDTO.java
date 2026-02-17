package com.ecovida.ecommerce_backend.controller.dto.usuario;

import com.ecovida.ecommerce_backend.models.Carrito;
import com.ecovida.ecommerce_backend.models.Role;
import com.ecovida.ecommerce_backend.models.Usuario;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.password.PasswordEncoder;


public record UsuarioRequestDTO(
        @NotNull(message = "El email no puede ser null")
        @NotBlank(message = "El email de usuario  es requerido")
        String email,

        @NotNull(message = "El password no puede ser null")
        @NotBlank(message = "El password es requerido")
        String password,

        @NotNull(message = "El nombre no puede ser null")
        @NotBlank(message = "El nombre es requerido")
        String nombre,

        @NotNull(message = "El apellido no puede ser null")
        @NotBlank(message = "El apellido es requerido")
        String apellido,

        @NotNull(message = "La provincia no puede ser null")
        @NotBlank(message = "La provincia es requerida")
        String provincia,

        @NotNull(message = "La localidad no puede ser null")
        @NotBlank(message = "La localidad es requerida")
        String localidad,

        @NotNull(message = "El codigo postal no puede ser null")
        @NotBlank(message = "El codigo postal es requerido")
        String codigoPostal,

        @NotNull(message = "El domicilio no puede ser null")
        @NotBlank(message = "El domicilio es requerido")
        String domicilio,

        @NotBlank(message = "El telefono es requerido")
        String telefono

    ){
        public Usuario aModelo() {
            return Usuario.builder()
                    .email(this.email)
                    .password(password)
                    .nombre(this.nombre)
                    .apellido(this.apellido)
                    .provincia(this.provincia)
                    .localidad(this.localidad)
                    .codigoPostal(this.codigoPostal)
                    .domicilio(this.domicilio)
                    .telefono(this.telefono)
                    .role(Role.USER)
                    .carrito(new Carrito())
                    .build();
        }
}