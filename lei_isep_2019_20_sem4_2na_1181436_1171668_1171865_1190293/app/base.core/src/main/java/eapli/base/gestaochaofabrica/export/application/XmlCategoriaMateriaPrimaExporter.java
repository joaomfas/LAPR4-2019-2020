/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author mdias
 */
public class XmlCategoriaMateriaPrimaExporter implements ChaoFabricaExporter<CategoriaMateriaPrima>{
    private PrintWriter stream;
    
    @Override
    public void begin(String filename) throws IOException {
        stream = new PrintWriter(new FileWriter(filename));
        stream.println("<CategoriasMateriaPrima>");
    }

    @Override
    public void element(CategoriaMateriaPrima cat, Date dataI, Date dataF) {
        stream.println("<CategoriaMateriaPrima>");
        stream.printf("<CodigoCategoria>%s</CodigoCategoria>%n", cat.codigoCategoria());
        stream.printf("<Descricao>%s</Descricao>%n", cat.descricao());
        stream.println("</CategoriaMateriaPrima>");
    }

    @Override
    public void elementSeparator() {
        // nothing to do
    }

    @Override
    public void end() {
        stream.println("</CategoriasMateriaPrima>");
        stream.close();
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            stream.close();
        }
    }
}
