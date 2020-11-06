
package eapli.base.app.backoffice.presentation.spm;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author mdias
 */
public class EstadoProcessamentoDeLinhaPrinter implements Visitor<LinhaProducao>{
    public EstadoProcessamentoDeLinhaPrinter() {
    }

    @Override
    public void visit(LinhaProducao visitee) {
        System.out.println("Linha de produção: " + visitee.identity());
        System.out.println("Estado de processamento recorrente: " + visitee.estadoProcessamentoRecorrente());
        System.out.println("Data último processamento: " + visitee.dataProcessamento());
    }
}
