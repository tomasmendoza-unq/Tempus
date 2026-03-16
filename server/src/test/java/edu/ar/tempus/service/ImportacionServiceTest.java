package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class ImportacionServiceTest {

    @Autowired
    private ImportacionService importacionService;

    @Autowired
    private ResetService resetService;

    @Autowired
    private MateriaService materiaService;

    private InputStream pdfInputStream;

    @BeforeEach
    public void setUp() throws IOException {
        ClassPathResource resource = new ClassPathResource("pdfs/BLOG_Oferta-TPI-2026c1-sitio_0403026.pdf");
        pdfInputStream = resource.getInputStream();
    }

    @Test
    public void cargarOfertaAcademicaDeberiaProcesarElPdfReal() {
        importacionService.cargarOfertaAcademica(pdfInputStream);

        List<Materia> materias = materiaService.recuperarTodos();

        assertFalse(materias.isEmpty());
        assertTrue(materias.stream().anyMatch(m -> m.getMateriaNombre().equalsIgnoreCase("Bases de Datos")));
        assertTrue(materias.stream().anyMatch(m -> m.getMateriaNombre().equalsIgnoreCase("Matemática I")));

        Materia bd = materias.stream()
                .filter(m -> m.getMateriaNombre().equalsIgnoreCase("Bases de Datos"))
                .findFirst()
                .orElseThrow();

        assertFalse(bd.getComisiones().isEmpty(), "La materia Bases de Datos debería tener comisiones");
        assertTrue(bd.getComisiones().size() >= 6);


        Materia objetos3 = materias.stream()
                .filter(m -> m.getMateriaNombre().contains("Objetos III"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("No se encontró la materia Programación con Objetos III"));

        boolean tieneComisionCritica = objetos3.getComisiones().stream()
                .anyMatch(c -> c.getComisionNombre().contains("1051-1-G14"));

        assertTrue(tieneComisionCritica, "Debería haber parseado la comisión 1051-1-G14 a pesar del horario 9:00");

        var comisionObjetos = objetos3.getComisiones().stream()
                .filter(c -> c.getComisionNombre().contains("1051-1-G14"))
                .findFirst()
                .get();

        assertFalse(comisionObjetos.getClases().isEmpty(), "La comisión debería tener horarios asociados");
    }

    @AfterEach
    public void tearDown() throws IOException {
        pdfInputStream.close();
        resetService.resetAll();
    }
}
