package edu.ar.tempus.controller.dto.usuario;

import edu.ar.tempus.controller.dto.comision.ComisionDTOResponseSimple;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.model.Role;
import edu.ar.tempus.model.Usuario;

import java.util.List;

public record UsuarioResponseDetallesDTO(
        Long id,
        String email,
        String nombre,
        String apellido,
        String telefono,
        List<ComisionDTOResponseSimple> comisiones,
        List<MateriaDTOResponseSimple> materiaDTOResponseSimples
) {
    public static UsuarioResponseDetallesDTO desdeModelo(Usuario usuario) {
        return new UsuarioResponseDetallesDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getComisiones().stream().map(ComisionDTOResponseSimple::desdeModelo).toList(),
                usuario.getMateriasAprobadas().stream().map(MateriaDTOResponseSimple::desdeModelo).toList()
        );
    }
}
