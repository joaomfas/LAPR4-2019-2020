package eapli.base.app.smm.console.presentation.gestaomonitorizacao;

import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.framework.visitor.Visitor;

public class MaquinaEstadoPrinter implements Visitor<Maquina> {

    @Override
    public void visit(Maquina visitee) {
        String ip = "";
        if(visitee.ipAddress()!=null)
            ip = visitee.ipAddress().toString();
        System.out.printf("%-30s%-30s%-30s", visitee.identity(), ip, visitee.estado());
    }
    
}
