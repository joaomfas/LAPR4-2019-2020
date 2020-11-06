package eapli.base.app.backoffice.presentation.maquinas;

import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.framework.visitor.Visitor;

public class MaquinasPrinter implements Visitor<Maquina> {

    @Override
    public void visit(Maquina visitee) {
        System.out.printf("%-10s", visitee.identity());
        //System.out.printf("%-25s%-50s%-20s%-20s", visitee.identity(),
        //        visitee.descricaoCompleta(), visitee.hasFichaProducao(), visitee.unidadeMedida().toString());
    }
    
}
