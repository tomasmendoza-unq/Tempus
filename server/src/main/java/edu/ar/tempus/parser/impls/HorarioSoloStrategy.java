package edu.ar.tempus.parser.impls;

import edu.ar.tempus.parser.LineaStrategy;
import edu.ar.tempus.parser.ParserContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Order(3)
@Component
public class HorarioSoloStrategy implements LineaStrategy {

    private static final Pattern PATRON = Pattern.compile(
            "^(?:Lun|Mar|Mie|Mié|Jue|Vie|Sab|Sáb)\\s+\\d{1,2}:\\d{2}.*$"
    );

    @Override
    public boolean matches(String linea, ParserContext ctx) {
        return PATRON.matcher(linea).matches();
    }

    @Override
    public void ejecutar(String linea, ParserContext ctx) {
        String actual = ctx.getHorarioPendiente();

        if (actual == null) {
            ctx.setHorarioPendiente(linea.trim());
        } else {
            ctx.setHorarioPendiente(actual + " " + linea.trim());
        }
    }
}