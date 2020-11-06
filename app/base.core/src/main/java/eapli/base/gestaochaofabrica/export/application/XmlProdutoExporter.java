package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.domain.Componente;
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
public class XmlProdutoExporter implements ChaoFabricaExporter<Produto> {

    private PrintWriter stream;

    @Override
    public void begin(String filename) throws IOException {
        stream = new PrintWriter(new FileWriter(filename));
        stream.println("<Produtos>");
    }

    @Override
    public void element(Produto p, Date dataI, Date dataF) {
        stream.println("<Produto>");
        stream.printf("<CodigoFabrico>%s</CodigoFabrico>%n", p.identity());
        stream.printf("<CodigoComercial>%s</CodigoComercial>%n", p.codigoComercial());
        stream.printf("<DescricaoBreve>%s</DescricaoBreve>%n", p.descricaoBreve());
        stream.printf("<DescricaoCompleta>%s</DescricaoCompleta>%n", p.descricaoCompleta());
        stream.printf("<Categoria>%s</Categoria>%n", p.categoria());
        stream.printf("<UnidadeMedida>%s</UnidadeMedida>%n", p.unidadeMedida());
        stream.printf("<HasFichaProducao>%s</HasFichaProducao>%n", p.hasFichaProducao());
        if (p.hasFichaProducao()) {
            stream.println("<FichaProducao>");
            stream.println("<Componentes>");
            Set<Componente> componentes = new HashSet<>();
            componentes = p.fichaProducao().listaComponentes();
            for (Componente comp : componentes) {
                stream.println("<Componente>");
                if (!comp.isProduto()) {
                    stream.printf("<MateriaPrima>%s</MateriaPrima>%n", comp.materiaPrima().identity());
                } else {
                    stream.printf("<Produto>%s</Produto>%n", comp.produto().identity());
                }
                stream.printf("<Quantidade>%s</Quantidade>%n", comp.quantidade());
                stream.println("</Componente>");
            }
            stream.println("</Componentes>");
            stream.println("</FichaProducao>");
        }
        stream.println("</Produto>");
    }

    @Override
    public void elementSeparator() {
        // nothing to do
    }

    @Override
    public void end() {
        stream.println("</Produtos>");
        stream.close();
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            stream.close();
        }
    }
}
