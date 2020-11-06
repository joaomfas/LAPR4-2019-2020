
package eapli.base.app.backoffice.presentation.linhasproducao;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author mdias
 */
public class LinhasProducaoPrinter implements Visitor<LinhaProducao>{
    @Override
    public void visit(LinhaProducao visitee) {
        System.out.println("Linha de Produção: "+ visitee.identity());
    }
}
