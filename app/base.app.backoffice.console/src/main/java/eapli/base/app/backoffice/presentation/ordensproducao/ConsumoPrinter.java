package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.gestaoordensproducao.domain.Consumo;
import eapli.framework.visitor.Visitor;

public class ConsumoPrinter implements Visitor<Consumo> {

    @Override
    public void visit(Consumo visitee) {
        if (visitee.isProduto()) {
            System.out.println(" ");
            System.out.printf("%-15s%-15s%-15s%-10s%-10s", visitee.dataRegistoStr(), visitee.produto().codigoComercial(),
                    visitee.quantidade(), visitee.desvio().intValue(), visitee.deposito().identity());
        } else {
            System.out.println(" ");
            System.out.printf("%-15s%-15s%-15s%-10s%-10s", visitee.dataRegistoStr(), visitee.materiaPrima().codigo(),
                    visitee.quantidade(), visitee.desvio().intValue(), visitee.deposito().identity());
        }
    }

}
