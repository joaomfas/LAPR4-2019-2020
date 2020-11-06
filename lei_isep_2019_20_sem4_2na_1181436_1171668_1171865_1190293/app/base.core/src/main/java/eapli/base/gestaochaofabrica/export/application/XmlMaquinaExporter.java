package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaomaquinas.domain.Maquina;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
/**
 *
 * @author mdias
 */
public class XmlMaquinaExporter implements ChaoFabricaExporter<Maquina>{
    private PrintWriter stream;
    
    @Override
    public void begin(String filename) throws IOException {
        stream = new PrintWriter(new FileWriter(filename));
        stream.println("<Maquinas>");
    }

    @Override
    public void element(Maquina m, Date dataI, Date dataF) {
        stream.println("<Maquina>");
        stream.printf("<CodigoInterno>%s</CodigoInterno>%n", m.codInterno());
        stream.printf("<DataInstalacao>%s</DataInstalacao>%n", m.datainstalacao().toString());
        stream.printf("<Descricao>%s</Descricao>%n", m.descricao());
        stream.printf("<Marca>%s</Marca>%n", m.marca());
        stream.printf("<Modelo>%s</Modelo>%n", m.modelo());
        stream.printf("<NumSerie>%s</NumSerie>%n", m.numserie());
        stream.printf("<NumeroIdentificacaoUnico>%s</NumeroIdentificacaoUnico>%n", m.numeroIdentificacaoUnico().toString());
        stream.println("</Maquina>");
    }

    @Override
    public void elementSeparator() {
        // nothing to do
    }

    @Override
    public void end() {
        stream.println("</Maquinas>");
        stream.close();
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            stream.close();
        }
    }

}
