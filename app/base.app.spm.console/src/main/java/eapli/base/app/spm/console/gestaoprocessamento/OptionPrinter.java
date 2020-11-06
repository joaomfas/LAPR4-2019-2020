package eapli.base.app.spm.console.gestaoprocessamento;

import eapli.framework.visitor.Visitor;


@SuppressWarnings("squid:S106")
public class OptionPrinter implements Visitor<String> {

    @Override
    public void visit(String visitee) {
        System.out.printf("%-20s", visitee);
    }
}