/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.domain.Sequencia;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mdias
 */
public class XmlLinhaProducaoExporter implements ChaoFabricaExporter<LinhaProducao> {

    private PrintWriter stream;

    @Override
    public void begin(String filename) throws IOException {
        stream = new PrintWriter(new FileWriter(filename));
        stream.println("<LinhasProducao>");
    }

    @Override
    public void element(LinhaProducao lp, Date dataI, Date dataF) {
        stream.println("<LinhaProducao>");
        stream.printf("<Id>%s</Id>%n", lp.identity());
        stream.println("<Sequencias>");
        List<Sequencia> sequencias = new ArrayList<>();
        sequencias = lp.listaSequencia();
        for (Sequencia sequencia : sequencias) {
            stream.println("<Sequencia>");
            stream.printf("<NrSequencia>%s</NrSequencia>%n", sequencia.numSquenciaMaquina());
            stream.printf("<Maquina>%s</Maquina>%n", sequencia.maquina().codInterno());
            stream.println("</Sequencia>");
        }
        stream.println("</Sequencias>");
        stream.println("</LinhaProducao>");
    }

    @Override
    public void elementSeparator() {
        // nothing to do
    }

    @Override
    public void end() {
        stream.println("</LinhasProducao>");
        stream.close();
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            stream.close();
        }
    }
}
