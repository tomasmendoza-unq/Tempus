package edu.ar.tempus.parser;

public interface LineaStrategy {
    boolean matches(String linea, ParserContext ctx);

    void ejecutar(String linea, ParserContext ctx);
}
