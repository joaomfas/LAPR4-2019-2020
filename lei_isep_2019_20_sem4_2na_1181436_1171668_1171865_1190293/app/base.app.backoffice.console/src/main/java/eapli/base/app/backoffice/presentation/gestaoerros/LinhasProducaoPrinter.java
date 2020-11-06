package eapli.base.app.backoffice.presentation.gestaoerros;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.framework.visitor.Visitor;

public class LinhasProducaoPrinter implements Visitor<LinhaProducao>{

    @Override
    public void visit(LinhaProducao visitee) {
        System.out.printf("%-10s", visitee.identity());
    }
    
}
