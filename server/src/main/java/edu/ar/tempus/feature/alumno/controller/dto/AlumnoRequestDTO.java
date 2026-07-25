package edu.ar.tempus.feature.alumno.controller.dto;


import edu.ar.tempus.model.Role;
import edu.ar.tempus.model.Usuario;
import jakarta.validation.constraints.*;


public record AlumnoRequestDTO(
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

        @NotNull(message = "La carrera es obligatoria")
        Long carreraId

    ){
        public Usuario aModelo() {
            return Usuario.builder()
                    .email(this.email)
                    .password(password)
                    .nombre(this.nombre)
                    .apellido(this.apellido)
                    .role(Role.ALUMNO)
                    .build();
        }
}