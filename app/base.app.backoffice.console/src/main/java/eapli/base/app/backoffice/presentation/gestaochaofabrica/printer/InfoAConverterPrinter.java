package eapli.base.app.backoffice.presentation.gestaochaofabrica.printer;

import eapli.base.gestaochaofabrica.export.application.OpcConversaoInfo;
import eapli.framework.visitor.Visitor;

public class InfoAConverterPrinter implements Visitor<OpcConversaoInfo>{
        
    @Override
        public void visit(OpcConversaoInfo visitee) {
            System.out.printf("%-20s", visitee.getValue());
        }

}
