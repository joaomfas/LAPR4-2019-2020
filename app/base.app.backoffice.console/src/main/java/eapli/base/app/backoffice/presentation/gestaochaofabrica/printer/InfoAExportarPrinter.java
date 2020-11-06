package eapli.base.app.backoffice.presentation.gestaochaofabrica.printer;

import eapli.base.gestaochaofabrica.export.application.InfoAExportar;
import eapli.framework.visitor.Visitor;

public class InfoAExportarPrinter implements Visitor<InfoAExportar>{
        
    @Override
        public void visit(InfoAExportar visitee) {
            System.out.printf("%-20s", visitee.getValue());
        }

}
