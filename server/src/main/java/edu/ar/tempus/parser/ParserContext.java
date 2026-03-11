package edu.ar.tempus.parser;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.parser.impls.horario.ClaseHorarioParser;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class ParserContext {
    private Map<String, Materia> materias = new LinkedHashMap<>();
    private String nombrePendiente;
    private String codigoPendiente;
    private String horarioPendiente;

    public void agregarComision(String nombreMateria, String codigoComision, String horarioExtra) {
        Materia materia = materias.computeIfAbsent(nombreMateria,
                nombre -> Materia.builder().materiaNombre(nombre).build()
        );

        String horarioCompleto = (horarioPendiente != null ? horarioPendiente + " " : "")
                + (horarioExtra != null ? horarioExtra : "");

        List<ClaseHorario> clases = ClaseHorarioParser.parsear(horarioCompleto.trim());

        Comision comision = new Comision();
        comision.setClases(clases);
        comision.setComisionNombre(codigoComision);
        materia.agregarComision(comision);

        horarioPendiente = null;
    }
}