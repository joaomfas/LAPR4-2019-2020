package eapli.base.app.smm.console.presentation.gestaomonitorizacao;

import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.smm.application.EnviarPedidoResetMaquinaController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.List;

public class EnviarPedidoResetUI extends AbstractUI {

    EnviarPedidoResetMaquinaController controller = new EnviarPedidoResetMaquinaController();
    
    @Override
    protected boolean doShow() {
        List<Maquina> maquinas = controller.maquinasMonitorizadas();
        
        
        if(maquinas.isEmpty()) {
            //throw new IllegalArgumentException("Não existem linhas registadas.");
            System.out.println("Não existem maquinas registadas.");
            return false;
        }
        System.out.printf("%-30s %-30s %-30s", "Maquina", "IP", "Estado");
        final SelectWidget<Maquina> selector = new SelectWidget<>("", maquinas, new MaquinaEstadoPrinter());
        selector.show();
        
        Maquina maquina = selector.selectedElement();
        
        controller.resetMaquina(maquina);
        
        return false;
    }

    @Override
    public String headline() {
        return "Pedido Reset Maquina";
    }
    
}
