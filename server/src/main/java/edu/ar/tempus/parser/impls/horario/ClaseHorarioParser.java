package edu.ar.tempus.parser.impls.horario;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.DiasSemana;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter; // Importar esto
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClaseHorarioParser {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    private static final Pattern PATRON_BANDA = Pattern.compile(
            "(Lun|Mar|Mie|Mié|Jue|Vie|Sab|Sáb)\\s+(\\d{1,2}:\\d{2})\\s+a\\s+(\\d{1,2}:\\d{2})"
    );

    private static final Map<String, DiasSemana> DIAS = Map.of(
            "Lun", DiasSemana.LUNES,
            "Mar", DiasSemana.MARTES,
            "Mie", DiasSemana.MIERCOLES,
            "Mié", DiasSemana.MIERCOLES,
            "Jue", DiasSemana.JUEVES,
            "Vie", DiasSemana.VIERNES,
            "Sab", DiasSemana.SABADO,
            "Sáb", DiasSemana.SABADO
    );

    public static List<ClaseHorario> parsear(String horarioStr) {
        if (horarioStr == null || horarioStr.isBlank()) return new ArrayList<>();

        List<ClaseHorario> clases = new ArrayList<>();
        Matcher m = PATRON_BANDA.matcher(horarioStr);

        while (m.find()) {
            clases.add(ClaseHorario.builder()
                    .dia(DIAS.get(m.group(1)))
                    .inicio(LocalTime.parse(m.group(2), TIME_FORMATTER))
                    .fin(LocalTime.parse(m.group(3), TIME_FORMATTER))
                    .build());
        }

        return clases;
    }
}