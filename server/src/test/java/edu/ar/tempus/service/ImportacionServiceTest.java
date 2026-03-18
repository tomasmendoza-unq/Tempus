package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile pdfFile;

    @BeforeEach
    public void setUp() throws IOException {
        ClassPathResource resource =
                new ClassPathResource("pdfs/BLOG_Oferta-TPI-2026c1-sitio_0403026.pdf");

        pdfFile = new MockMultipartFile(
                "file",
                "oferta.pdf",
                "application/pdf",
                resource.getInputStream()
        );
    }
    @Test
    public void previewOfertaAcademicaDeberiaParsearElPdfReal() {

        List<Materia> materias = importacionService.preview(pdfFile);

        assertFalse(materias.isEmpty());

        assertTrue(materias.stream()
                .anyMatch(m -> m.getMateriaNombre().equalsIgnoreCase("Bases de Datos")));

        assertTrue(materias.stream()
                .anyMatch(m -> m.getMateriaNombre().equalsIgnoreCase("Matemática I")));

        Materia bd = materias.stream()
                .filter(m -> m.getMateriaNombre().equalsIgnoreCase("Bases de Datos"))
                .findFirst()
                .orElseThrow();

        assertFalse(bd.getComisiones().isEmpty());
    }

    @AfterEach
    public void tearDown()  {
        resetService.resetAll();
    }
}
