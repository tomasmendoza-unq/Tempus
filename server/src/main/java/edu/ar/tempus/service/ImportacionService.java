package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface ImportacionService {

    List<Materia> preview(MultipartFile pdf);
}
