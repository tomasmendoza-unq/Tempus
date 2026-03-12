package edu.ar.tempus.parser.impls;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.parser.LineaStrategy;
import edu.ar.tempus.parser.OfertaParserService;
import edu.ar.tempus.parser.ParserContext;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OfertaParserServiceImpl implements OfertaParserService {

    private final List<LineaStrategy> strategies;

    public OfertaParserServiceImpl(List<LineaStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public List<Materia> parsear(InputStream pdfInputStream) {

        ParserContext ctx = new ParserContext();

        try (RandomAccessReadBuffer source = new RandomAccessReadBuffer(pdfInputStream);
             PDDocument document = Loader.loadPDF(source)) {

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);

            String[] lineas = stripper.getText(document).split("\\r?\\n");

            for (String linea : lineas) {

                linea = linea.trim();
                if (linea.isEmpty()) continue;

                for (LineaStrategy strategy : strategies) {
                    if (strategy.matches(linea, ctx)) {
                        System.out.println("LINEA: [" + linea + "]");
                        System.out.println("STRATEGY: " + strategy.getClass().getSimpleName());

                        strategy.ejecutar(linea, ctx);

                        System.out.println("CTX nombrePendiente: " + ctx.getNombrePendiente());
                        System.out.println("CTX codigoPendiente: " + ctx.getCodigoPendiente());
                        System.out.println("CTX horarioPendiente: " + ctx.getHorarioPendiente());
                        System.out.println("------------------------------------------------");

                        break;
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error parseando PDF", e);
        }

        return new ArrayList<>(ctx.getMaterias().values());
    }
}