package edu.ar.tempus.service.impl;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.service.ImportacionService;
import edu.ar.tempus.service.MateriaService;
import edu.ar.tempus.parser.PdfParserClient;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
@Transactional
public class ImportacionServiceImpl implements ImportacionService {

    private final PdfParserClient ofertaParserService;

    private final MateriaService materiaService;

    public ImportacionServiceImpl(PdfParserClient ofertaParserService, MateriaService materiaService) {
        this.ofertaParserService = ofertaParserService;
        this.materiaService = materiaService;
    }

    @Override
    public void cargarOfertaAcademica(InputStream pdfInputStream) {

        List<Materia> materias = ofertaParserService.parsear(pdfInputStream);

        materiaService.guardarMaterias(materias);

    }

    @Override
    public Page<Materia> preview(MultipartFile pdf, Pageable pageable) {
        try {
            List<Materia> materias = ofertaParserService.parsear(pdf.getInputStream());

            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(), materias.size());

            return new PageImpl<>(materias.subList(start, end), pageable, materias.size());
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo el PDF", e);
        }
    }
}
