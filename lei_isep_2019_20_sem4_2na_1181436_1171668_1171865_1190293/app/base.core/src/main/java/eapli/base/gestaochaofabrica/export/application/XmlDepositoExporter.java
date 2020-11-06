/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaodepositos.domain.Deposito;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author mdias
 */
public class XmlDepositoExporter implements ChaoFabricaExporter<Deposito> {
    
    private PrintWriter stream;
    
    @Override
    public void begin(String filename) throws IOException {
        stream = new PrintWriter(new FileWriter(filename));
        stream.println("<Depositos>");
    }

    @Override
    public void element(Deposito d, Date dataI, Date dataF) {
        stream.println("<Deposito>");
        stream.printf("<CodigoDeposito>%s</CodigoDeposito>%n", d.identity());
        stream.printf("<Descricao>%s</Descricao>%n", d.descricao());
        stream.println("</Deposito>");
    }

    @Override
    public void elementSeparator() {
        // nothing to do
    }

    @Override
    public void end() {
        stream.println("</Depositos>");
        stream.close();
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            stream.close();
        }
    }


}
