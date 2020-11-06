package eapli.base.app.backoffice.presentation.gestaoerros;

import eapli.base.spm.application.processamento.validacao.exceptions.EnumErros;
import eapli.framework.visitor.Visitor;


public class TipoErroPrinter implements Visitor<EnumErros>{

    @Override
    public void visit(EnumErros visitee) {
        System.out.printf("%-25s", visitee.toString());
    }

}
