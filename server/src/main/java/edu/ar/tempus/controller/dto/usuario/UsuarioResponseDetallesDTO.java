package edu.ar.tempus.controller.dto.usuario;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponse;
import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponseSimple;
import edu.ar.tempus.controller.dto.comision.ComisionDTOResponseSimple;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.model.usuario.Usuario;

import java.util.List;

public record UsuarioResponseDetallesDTO(
        Long id,
        String email,
        String nombre,
        String apellido,
        String telefono,
        List<ComisionDTOResponseSimple> comisiones,
        List<MateriaDTOResponseSimple> materiaDTOResponseSimples,
        List<CarreraDTOResponse> carreras,
        CarreraDTOResponseSimple carreraActiva
) {
    public static UsuarioResponseDetallesDTO desdeModelo(Usuario usuario) {
        return new UsuarioResponseDetallesDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getComisiones().stream().map(ComisionDTOResponseSimple::desdeModelo).toList(),
                usuario.getMateriasAprobadas().stream().map(MateriaDTOResponseSimple::desdeModelo).toList(),
                usuario.getCarreras().stream().map(CarreraDTOResponse::desdeModelo).toList(),
                usuario.getCarreraActiva() != null
                        ? CarreraDTOResponseSimple.desdeModelo(usuario.getCarreraActiva())
                        : null
        );
    }
}
