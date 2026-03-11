package edu.ar.tempus.parser.impls;

import edu.ar.tempus.parser.LineaStrategy;
import edu.ar.tempus.parser.ParserContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Order(2)
@Component
public class SoloCodigoStrategy implements LineaStrategy {

    private static final Pattern PATRON = Pattern.compile(
            "^(\\d{3,6}(?:-[A-Z0-9]+){2,3})\\s*(.*)$"
    );

    @Override
    public boolean matches(String linea, ParserContext ctx) {
        return PATRON.matcher(linea).matches();
    }

    @Override
    public void ejecutar(String linea, ParserContext ctx) {

        Matcher m = PATRON.matcher(linea);

        if (!m.matches()) return;

        String codigo = m.group(1).trim();

        if (ctx.getNombrePendiente() != null) {

            ctx.agregarComision(ctx.getNombrePendiente(), codigo, null);
            ctx.setNombrePendiente( null);

        } else {

            ctx.setCodigoPendiente(codigo);
        }
    }
}