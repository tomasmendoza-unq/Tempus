package edu.ar.tempus.parser.impls;

import edu.ar.tempus.parser.LineaStrategy;
import edu.ar.tempus.parser.ParserContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(6)
@Component
public class ResetStrategy implements LineaStrategy {

    @Override
    public boolean matches(String linea, ParserContext ctx) {
        return true;
    }

    @Override
    public void ejecutar(String linea, ParserContext ctx) {
        if (ctx.getCodigoPendiente() == null) {
            ctx.setNombrePendiente(null);
        }
    }
}