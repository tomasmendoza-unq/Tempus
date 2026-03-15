package edu.ar.tempus.parser.impls;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.DiasSemana;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.parser.MultipartInputStreamFileResource;
import edu.ar.tempus.parser.PdfParserClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.io.InputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PdfParserClientImpl implements PdfParserClient {

    private final RestClient restClient = RestClient.create();

    private static final Map<String, DiasSemana> DIAS = Map.of(
            "Lun", DiasSemana.LUNES,
            "Mar", DiasSemana.MARTES,
            "Mie", DiasSemana.MIERCOLES,
            "Jue", DiasSemana.JUEVES,
            "Vie", DiasSemana.VIERNES,
            "Sab", DiasSemana.SABADO,
            "Sáb", DiasSemana.SABADO,
            "Dom", DiasSemana.DOMINGO
    );

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    @Override
    public List<Materia> parsear(InputStream pdfInputStream) {
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new MultipartInputStreamFileResource(pdfInputStream, "oferta.pdf"));

            ParserResponse response = restClient.post()
                    .uri("http://localhost:8000/parsear")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(body)
                    .retrieve()
                    .body(ParserResponse.class);

            return response.comisiones().stream()
                    .collect(Collectors.groupingBy(ComisionDTO::actividad, LinkedHashMap::new, Collectors.toList()))
                    .entrySet().stream()
                    .map(entry -> Materia.builder()
                            .materiaNombre(entry.getKey())
                            .comisiones(entry.getValue().stream()
                                    .map(c -> Comision.builder()
                                            .comisionNombre(c.comision())
                                            .modalidad(c.modalidad())
                                            .clases(parsearHorario(c.horario()))
                                            .build())
                                    .toList())
                            .build())
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Error llamando a FastAPI", e);
        }
    }

    private List<ClaseHorario> parsearHorario(String horario) {
        if (horario == null || horario.isBlank() || horario.contains("Sin horario")) return List.of();

        return Arrays.stream(horario.split(" / "))
                .map(String::trim)
                .filter(parte -> !parte.isBlank())
                .map(parte -> {
                    parte = parte.replaceAll("\\(Virtual\\)", "").trim();
                    String[] tokens = parte.split("\\s+");
                    // Verificar que tenga al menos 4 tokens: "Lun 09:30 a 12:59"
                    if (tokens.length < 4) return null;
                    DiasSemana dia = DIAS.getOrDefault(tokens[0], null);
                    if (dia == null) return null;
                    try {
                        LocalTime inicio = LocalTime.parse(tokens[1], TIME_FORMATTER);
                        LocalTime fin    = LocalTime.parse(tokens[3], TIME_FORMATTER);
                        return ClaseHorario.builder().dia(dia).inicio(inicio).fin(fin).build();
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    // Records internos para deserializar la respuesta de FastAPI
    private record ComisionDTO(String actividad, String comision, String modalidad, String horario) {}
    private record ParserResponse(List<ComisionDTO> comisiones) {}
}
