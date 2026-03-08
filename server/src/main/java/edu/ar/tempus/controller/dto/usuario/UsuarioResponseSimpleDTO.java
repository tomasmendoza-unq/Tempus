package edu.ar.tempus.controller.dto.usuario;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponseSimple;
import edu.ar.tempus.model.Usuario;

import java.util.List;

public record UsuarioResponseSimpleDTO(
        String email,
        String nombre,
        List<CarreraDTOResponseSimple> carreras
) {

    public static UsuarioResponseSimpleDTO desdeModelo(Usuario usuario) {
        return new UsuarioResponseSimpleDTO(
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getCarreras().stream().map(CarreraDTOResponseSimple::desdeModelo).toList()
        );
    }
}
