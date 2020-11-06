package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.gestaoordensproducao.domain.Estorno;
import eapli.framework.visitor.Visitor;


public class EstornoPrinter implements Visitor<Estorno> {

    @Override
    public void visit(Estorno visitee) {
        System.out.println(" ");
        System.out.printf("%-15s%-15s%-15s%-10s", visitee.dataRegistoStr(), visitee.materiaPrima().codigo(),
                visitee.quantidade().intValue(), visitee.deposito().identity());
    }

}
