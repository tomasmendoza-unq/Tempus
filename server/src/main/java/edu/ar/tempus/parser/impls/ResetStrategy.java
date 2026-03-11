package edu.ar.tempus.parser.impls;

import edu.ar.tempus.parser.LineaStrategy;
import edu.ar.tempus.parser.ParserContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Order(6)
@Component
public class ResetStrategy implements LineaStrategy {

    private static final Pattern PATRON_HORARIO = Pattern.compile(
            "^(?:Lun|Mar|Mie|Mié|Jue|Vie|Sab|Sáb)\\b.*"
    );

    @Override
    public boolean matches(String linea, ParserContext ctx) {
        return true;
    }

    @Override
    public void ejecutar(String linea, ParserContext ctx) {
        if (PATRON_HORARIO.matcher(linea).matches()) {
            String actual = ctx.getHorarioPendiente();
            ctx.setHorarioPendiente(actual != null ? actual + " " + linea : linea);
        } else {
            if (ctx.getCodigoPendiente() == null) {
                ctx.setNombrePendiente(null);
            }
            ctx.setHorarioPendiente(null);
        }
    }
}