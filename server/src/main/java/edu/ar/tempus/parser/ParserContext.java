package edu.ar.tempus.parser;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class ParserContext {

    private Map<String, Materia> materias = new LinkedHashMap<>();

    private String nombrePendiente;
    private String codigoPendiente;

    public void agregarComision(String nombreMateria, String codigoComision) {

        Materia materia = materias.computeIfAbsent(
                nombreMateria,
                nombre -> Materia.builder().materiaNombre(nombre).build()
        );

        Comision comision = new Comision();
        materia.agregarComision(comision);
    }
}