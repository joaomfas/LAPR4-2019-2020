package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaoordensproducao.domain.Consumo;
import eapli.base.gestaoordensproducao.domain.Estorno;
import eapli.base.gestaoordensproducao.domain.EtapaProducao;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.Producao;
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
public class XmlExecucaoOrdemProducaoExporter implements ChaoFabricaExporter<ExecucaoOrdemProducao> {

    private PrintWriter stream;

    @Override
    public void begin(String filename) throws IOException {
        stream = new PrintWriter(new FileWriter(filename));
        stream.println("<ExecucoesOrdemProducao>");
    }

    @Override
    public void element(ExecucaoOrdemProducao eop, Date dataI, Date dataF) {
        stream.println("<ExecucaoOrdemProducao>");
        stream.printf("<CodExecucaoOrdemProducao>%s</CodExecucaoOrdemProducao>%n", eop.identity());
        stream.printf("<CodOrdemProducao>%s</CodOrdemProducao>%n", eop.ordemProducao().identity());
        stream.println("<Consumos>");
        Set<Consumo> consumos = new HashSet<>();
        consumos = eop.consumos();
        for (Consumo c : consumos) {
            if (c.dataRegisto().getTime().after(dataI) || c.dataRegisto().getTime().equals(dataI)
                    || c.dataRegisto().getTime().before(dataF) || c.dataRegisto().getTime().equals(dataF)) {
                stream.println("<Consumo>");
                if (c.isProduto()) {
                    stream.printf("<Produto>%s</Produto>%n", c.produto().identity());
                } else {
                    stream.printf("<MateriaPrima>%s</MateriaPrima>%n", c.materiaPrima().identity());
                }
                stream.printf("<Quantidade>%s</Quantidade>%n", c.quantidade());
                stream.printf("<Deposito>%s</Deposito>%n", c.deposito().identity());
                stream.printf("<Desvio>%s</Desvio>%n", c.desvio());
                stream.printf("<Data>%s</Data>%n", c.dataRegistoStr());
                stream.println("</Consumo>");
            }
        }
        stream.println("</Consumos>");
        stream.println("<Producoes>");
        Set<Producao> producoes = new HashSet<>();
        producoes = eop.producoes();
        for (Producao p : producoes) {
            if (p.dataRegisto().getTime().after(dataI) || p.dataRegisto().getTime().equals(dataI)
                    || p.dataRegisto().getTime().before(dataF) || p.dataRegisto().getTime().equals(dataF)) {
                stream.println("<Producao>");
                stream.printf("<Produto>%s</Produto>%n", p.produto().identity());
                stream.printf("<Quantidade>%s</Quantidade>%n", p.quantidade());
                stream.printf("<Deposito>%s</Deposito>%n", p.deposito().identity());
                stream.printf("<Lote>%s</Lote>%n", p.lote());
                stream.printf("<Data>%s</Data>%n", p.dataRegistoStr());
                stream.println("</Producao>");
            }
        }
        stream.println("</Producoes>");
        stream.println("<Estornos>");
        Set<Estorno> estornos = new HashSet<>();
        estornos = eop.estornos();
        for (Estorno e : estornos) {
            if (e.dataRegisto().getTime().after(dataI) || e.dataRegisto().getTime().equals(dataI)
                    || e.dataRegisto().getTime().before(dataF) || e.dataRegisto().getTime().equals(dataF)) {
                stream.println("<Estorno>");
                stream.printf("<MateriaPrima>%s</MateriaPrima>%n", e.materiaPrima().identity());
                stream.printf("<Quantidade>%s</Quantidade>%n", e.quantidade());
                stream.printf("<Deposito>%s</Deposito>%n", e.deposito().identity());
                stream.printf("<Data>%s</Data>%n", e.dataRegistoStr());
                stream.println("</Estorno>");
            }
        }
        stream.println("</Estornos>");
        stream.println("<Etapas>");
        Set<EtapaProducao> etapas = new HashSet<>();
        etapas = eop.etapas();
        for (EtapaProducao etp : etapas) {
            stream.println("<Etapa>");
            stream.printf("<Maquina>%s</Maquina>%n", etp.maquina().identity());
            stream.printf("<TempoBruto>%s</TempoBruto>%n", etp.tempoBruto().toString());
            stream.printf("<TempoEfetivo>%s</TempoEfetivo>%n", etp.tempoEfetivo().toString());
            stream.println("</Etapa>");
        }
        stream.println("</Etapas>");
        stream.println("</ExecucaoOrdemProducao>");
    }

    @Override
    public void elementSeparator() {
        // nothing to do
    }

    @Override
    public void end() {
        stream.println("</ExecucoesOrdemProducao>");
        stream.close();
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            stream.close();
        }
    }
}
