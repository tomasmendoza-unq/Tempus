package edu.ar.tempus.parser.impls;

import edu.ar.tempus.parser.LineaStrategy;
import edu.ar.tempus.parser.ParserContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Order(4)
@Component
public class NombreTrasCodigoStrategy implements LineaStrategy {

    private static final Pattern PATRON = Pattern.compile(
            "^(.+?)\\s+(?:Asincrónica|Asincronica|Sincrónica|Presencial).*$"
    );

    @Override
    public boolean matches(String linea, ParserContext ctx) {
        return ctx.getCodigoPendiente() != null && PATRON.matcher(linea).matches();
    }

    @Override
    public void ejecutar(String linea, ParserContext ctx) {
        Matcher m = PATRON.matcher(linea);
        if (!m.matches()) return;

        String nombre = m.group(1).trim();

        ctx.agregarComision(nombre, ctx.getCodigoPendiente(), null);

        ctx.setCodigoPendiente(null);
        ctx.setNombrePendiente(null);
    }
}