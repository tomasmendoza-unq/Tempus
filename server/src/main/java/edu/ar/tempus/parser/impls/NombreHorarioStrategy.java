package edu.ar.tempus.parser.impls;

import edu.ar.tempus.parser.LineaStrategy;
import edu.ar.tempus.parser.ParserContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Order(4)
@Component
public class NombreHorarioStrategy implements LineaStrategy {

    private static final Pattern PATRON = Pattern.compile(
            "^(.+?)\\s+(?:Lun|Mar|Mie|Mié|Jue|Vie|Sab|Sáb)\\s+\\d{1,2}:\\d{2}.*$"
    );

    @Override
    public boolean matches(String linea, ParserContext ctx) {
        Matcher m = PATRON.matcher(linea);
        if (!m.matches()) return false;
        return esPosibleNombre(m.group(1).trim());
    }

    @Override
    public void ejecutar(String linea, ParserContext ctx) {
        Matcher m = PATRON.matcher(linea);
        if (!m.matches()) return;
        ctx.setNombrePendiente(m.group(1).trim());
    }

    private boolean esPosibleNombre(String s) {
        return s.length() >= 3 &&
                !s.matches(".*\\d{1,2}:\\d{2}.*") &&
                !s.matches("(?i)^(Lun|Mar|Mie|Mié|Jue|Vie|Sab|Sáb)\\b.*");
    }
}