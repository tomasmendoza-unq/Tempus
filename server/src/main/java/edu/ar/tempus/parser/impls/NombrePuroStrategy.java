package edu.ar.tempus.parser.impls;

import edu.ar.tempus.parser.LineaStrategy;
import edu.ar.tempus.parser.ParserContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(5)
@Component
public class NombrePuroStrategy implements LineaStrategy {

    @Override
    public boolean matches(String linea, ParserContext ctx) {
        return esPosibleNombre(linea);
    }

    @Override
    public void ejecutar(String linea, ParserContext ctx) {
        if (ctx.getNombrePendiente() != null) {
            ctx.setNombrePendiente(ctx.getNombrePendiente() + " " + linea);
        } else {
            ctx.setNombrePendiente(linea);
        }
    }

    private boolean esPosibleNombre(String s) {
        return s.length() >= 3 &&
                !s.matches(".*\\d{2}:\\d{2}.*") &&
                !s.matches("(?i).*(Actividad|Comisión|Banda Horaria|Sede|Carrera:|actualización|Según oferta|Virtual|Asincrónica|Asincronica|Sincrónica|asignado|Presencial|Página).*");
    }
}
