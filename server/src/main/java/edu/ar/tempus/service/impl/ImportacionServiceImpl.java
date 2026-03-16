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

    public ImportacionServiceImpl(PdfParserClient ofertaParserService) {
        this.ofertaParserService = ofertaParserService;
    }



    @Override
    public List<Materia> preview(MultipartFile pdf) {
        try {
            return ofertaParserService.parsear(pdf.getInputStream());

        } catch (Exception e) {
            throw new RuntimeException("Error leyendo el PDF", e);
        }
    }
}
