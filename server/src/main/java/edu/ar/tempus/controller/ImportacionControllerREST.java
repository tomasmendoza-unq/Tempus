package edu.ar.tempus.controller;

import edu.ar.tempus.service.ImportacionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imports")
public final class ImportacionControllerREST {


    private final ImportacionService importacionService;

    public ImportacionControllerREST(ImportacionService importacionService) {
        this.importacionService = importacionService;
    }

}
