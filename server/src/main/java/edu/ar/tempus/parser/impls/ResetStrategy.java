package edu.ar.tempus.parser.impls;

import edu.ar.tempus.parser.LineaStrategy;
import edu.ar.tempus.parser.ParserContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Order(7)
@Component
public class ResetStrategy implements LineaStrategy {

    private static final Pattern PATRON_CONTINUACION_HORA =
            Pattern.compile("^\\d{1,2}:\\d{2}.*");

    @Override
    public boolean matches(String linea, ParserContext ctx) {
        return true;
    }

    @Override
    public void ejecutar(String linea, ParserContext ctx) {

        if (ctx.getHorarioPendiente() != null &&
                PATRON_CONTINUACION_HORA.matcher(linea).matches()) {

            ctx.setHorarioPendiente(
                    ctx.getHorarioPendiente() + " " + linea
            );

            return;
        }

        if (ctx.getCodigoPendiente() == null) {
            ctx.setNombrePendiente(null);
        }

        ctx.setHorarioPendiente(null);
    }
}