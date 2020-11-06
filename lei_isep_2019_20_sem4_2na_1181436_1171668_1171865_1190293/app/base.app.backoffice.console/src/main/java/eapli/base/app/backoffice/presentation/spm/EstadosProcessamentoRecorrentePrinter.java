
package eapli.base.app.backoffice.presentation.spm;

import eapli.base.gestaolinhasproducao.domain.EstadoProcessamentoRecorrente;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author mdias
 */
public class EstadosProcessamentoRecorrentePrinter implements Visitor<EstadoProcessamentoRecorrente>{
    public EstadosProcessamentoRecorrentePrinter() {
    }

    @Override
    public void visit(EstadoProcessamentoRecorrente visitee) {
        System.out.printf("%-10s", visitee.toString());
    }
}
