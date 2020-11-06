package eapli.base.app.backoffice.presentation.maquinas;

import eapli.base.gestaomaquinas.domain.MaquinaConfigFile;
import eapli.framework.visitor.Visitor;


public class FicheiroConfigPrinter implements Visitor<MaquinaConfigFile> {

    @Override
    public void visit(MaquinaConfigFile visitee) {
        System.out.printf("%-20s%-20s", visitee.toString(), visitee.marcadoParaEnvio());
    }

}
