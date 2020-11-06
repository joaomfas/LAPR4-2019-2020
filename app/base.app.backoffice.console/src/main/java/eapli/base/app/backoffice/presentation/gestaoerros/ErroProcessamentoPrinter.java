package eapli.base.app.backoffice.presentation.gestaoerros;

import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.framework.visitor.Visitor;


public class ErroProcessamentoPrinter implements Visitor<ErroProcessamento>{

    @Override
    public void visit(ErroProcessamento visitee) {
        System.out.printf("\n%-10s%-20s%-50s%-20s", visitee.identity(),
                visitee.estado().toString(), visitee.tipoErro().toString(), visitee.dataErroStr());
    }

}
