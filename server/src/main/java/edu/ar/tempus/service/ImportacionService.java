package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImportacionService {
    void cargarOfertaAcademica(InputStream pdfInputStream);

    Page<Materia> preview(MultipartFile pdf, Pageable pageable);
}
