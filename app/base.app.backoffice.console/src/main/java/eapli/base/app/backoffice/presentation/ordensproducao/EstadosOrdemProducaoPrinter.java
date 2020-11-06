package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.gestaoordensproducao.domain.EstadoOrdemProducao;
import eapli.framework.visitor.Visitor;


public class EstadosOrdemProducaoPrinter implements Visitor<EstadoOrdemProducao> {

    public EstadosOrdemProducaoPrinter() {
    }

    @Override
    public void visit(EstadoOrdemProducao visitee) {
        System.out.printf("%-10s", visitee.toString());
    }

}
