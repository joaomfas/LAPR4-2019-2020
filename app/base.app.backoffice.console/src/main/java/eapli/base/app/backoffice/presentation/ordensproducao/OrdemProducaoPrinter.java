package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.framework.visitor.Visitor;

public class OrdemProducaoPrinter implements Visitor<OrdemProducao> {

    @Override
    public void visit(OrdemProducao visitee) {
        System.out.println("Código: " + visitee.identity().toString());
        System.out.println("Código de fabrico do produto: " + visitee.produto().identity().toString());
        System.out.println("Quantidade: " + visitee.quantidade() + " " + visitee.produto().unidadeMedida().toString());
        System.out.println("Data de emissão: " + visitee.dataEmissao());
        System.out.println("Data de início: " + visitee.dataInicio());
        System.out.println("Data prevista de final: " + visitee.dataPrevista());
        System.out.println("Data de fim: " + visitee.dataFim());
        System.out.println("Encomendas associadas: " + visitee.encomendas().toString());
    }

}
