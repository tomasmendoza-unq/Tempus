package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.materia.MateriaDTOResponse;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.parser.PdfParserClient;
import edu.ar.tempus.service.ImportacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/imports")
public final class ImportacionControllerREST {

    private final ImportacionService importacionService;


    public ImportacionControllerREST(ImportacionService importacionService) {
        this.importacionService = importacionService;
    }

    @PostMapping("/preview")
    public ResponseEntity<Page<MateriaDTOResponse>> preview(
            @RequestParam("pdf") MultipartFile pdf,
            @PageableDefault(size = 200) Pageable pageable) {

        Page<Materia> preview = importacionService.preview(pdf, pageable);

        List<MateriaDTOResponse> materias = preview.getContent().stream().map(MateriaDTOResponse::desdeModelo).toList();

        Page<MateriaDTOResponse> response = new PageImpl<>(materias, pageable, preview.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/load")
    public ResponseEntity<String> load(@RequestParam("pdf") MultipartFile pdf) {
        try {
            importacionService.cargarOfertaAcademica(pdf.getInputStream());
            return ResponseEntity.ok().body("Se subió con éxito la oferta");
        } catch (IOException e) {

            return ResponseEntity.internalServerError().body("Error al leer el archivo PDF");
        }
    }


}
