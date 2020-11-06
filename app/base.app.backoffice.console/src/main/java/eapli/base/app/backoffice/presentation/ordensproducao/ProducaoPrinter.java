package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.gestaoordensproducao.domain.Producao;
import eapli.framework.visitor.Visitor;


public class ProducaoPrinter implements Visitor<Producao> {

    @Override
    public void visit(Producao visitee) {
        System.out.println(" ");
        System.out.printf("%-15s%-15s%-15s%-10s%-10s", visitee.dataRegistoStr(), visitee.produto().identity(),
                    visitee.quantidade(), visitee.desvio().intValue(), visitee.deposito().identity());
    }

}
