package edu.ar.tempus.parser;

import edu.ar.tempus.model.Materia;

import java.io.InputStream;
import java.util.List;

public interface PdfParserClient {
    List<Materia> parsear(InputStream pdfInputStream);
}
