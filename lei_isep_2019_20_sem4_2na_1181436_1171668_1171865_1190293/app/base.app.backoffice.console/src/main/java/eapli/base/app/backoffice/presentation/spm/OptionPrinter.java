
package eapli.base.app.backoffice.presentation.spm;

import eapli.framework.visitor.Visitor;

/**
 *
 * @author mdias
 */
@SuppressWarnings("squid:S106")
public class OptionPrinter implements Visitor<String>{
    @Override
    public void visit(String visitee) {
        System.out.printf("%-20s", visitee);
    }
}
