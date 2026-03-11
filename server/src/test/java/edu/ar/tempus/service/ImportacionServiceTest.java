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

        boolean tieneBasesDeDatos = materias.stream()
                .anyMatch(m -> m.getMateriaNombre().equalsIgnoreCase("Bases de Datos"));
        boolean tieneMatematica = materias.stream()
                .anyMatch(m -> m.getMateriaNombre().equalsIgnoreCase("Matemática I"));

        assertTrue(tieneBasesDeDatos);
        assertTrue(tieneMatematica);

        Materia bd = materias.stream()
                .filter(m -> m.getMateriaNombre().equalsIgnoreCase("Bases de Datos"))
                .findFirst()
                .orElseThrow();

        assertTrue(bd.getComisiones().size() >= 6 );
    }

    @AfterEach
    public void tearDown() throws IOException {
        pdfInputStream.close();
        resetService.resetAll();
    }
}
