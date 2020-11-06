package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.domain.Encomenda;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mdias
 */
public class XmlOrdemProducaoExporter implements ChaoFabricaExporter<OrdemProducao> {

    private PrintWriter stream;

    @Override
    public void begin(String filename) throws IOException {
        stream = new PrintWriter(new FileWriter(filename));
        stream.println("<OrdensProducao>");
    }

    @Override
    public void element(OrdemProducao op, Date dataI, Date dataF) {
        stream.println("<OrdemProducao>");
        stream.printf("<CodOrdemProducao>%s</CodOrdemProducao>%n", op.identity());
        stream.printf("<Produto>%s</Produto>%n", op.produto().identity());
        stream.printf("<Quantidade>%s</Quantidade>%n", op.quantidade());
        stream.printf("<DataEmissao>%s</DataEmissao>%n", op.dataEmissao().toString());
        if (op.dataInicio() != null) {
            stream.printf("<DataInicio>%s</DataInicio>%n", op.dataInicio().toString());
        }
        stream.printf("<DataPrevista>%s</DataPrevista>%n", op.dataPrevista().toString());
        if (op.dataFim() != null) {
            stream.printf("<DataFim>%s</DataFim>%n", op.dataFim().toString());
        }
        stream.printf("<Estado>%s</Estado>%n", op.estado());
        stream.println("<Encomendas>");
        Set<Encomenda> encomendas = new HashSet<>();
        encomendas = op.encomendas();
        for (Encomenda enc : encomendas) {
            stream.println("<Encomenda>");
            stream.printf("<CodigoEncomenda>%s</CodigoEncomenda>%n", enc.toString());
            stream.println("</Encomenda>");
        }
        stream.println("</Encomendas>");
        stream.println("</OrdemProducao>");
    }

    @Override
    public void elementSeparator() {
        // nothing to do
    }

    @Override
    public void end() {
        stream.println("</OrdensProducao>");
        stream.close();
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            stream.close();
        }
    }
}
