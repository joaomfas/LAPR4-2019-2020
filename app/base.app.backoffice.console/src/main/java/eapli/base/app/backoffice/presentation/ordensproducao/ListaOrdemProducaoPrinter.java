package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.framework.visitor.Visitor;


public class ListaOrdemProducaoPrinter implements Visitor<OrdemProducao> {

    public ListaOrdemProducaoPrinter() {
    }

    @Override
    public void visit(OrdemProducao visitee) {
        System.out.printf("%-10s", visitee.identity().codigoOrdemProducao());
    }

}
