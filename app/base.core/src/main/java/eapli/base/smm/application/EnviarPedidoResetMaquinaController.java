package eapli.base.smm.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomensagens.dominio.Codes;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.dominio.Versoes;
import eapli.base.smm.server.SmmServer;
import eapli.framework.application.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EnviarPedidoResetMaquinaController implements Controller {

    public boolean resetMaquina(Maquina maquina) {
        
        try {
            
            int uid = Integer.valueOf(maquina.numeroIdentificacaoUnico().toString());

            Mensagem mensagem = new Mensagem(Versoes.VERS_0.getVersao(), Codes.RESET.getCode(), uid, 0, "");
            
            Mensagem resposta = new ComunicacaoUDPService(maquina.ipAddress(), SmmServer.PORTA, maquina.timeOut()).enviaMensagem(mensagem);

            if (resposta != null) {
                System.out.println("Resposta ao pedido reset : ");
                System.out.println("Maquina " + maquina.identity() + " : " + resposta.code());
                System.out.println(resposta.rawData());
            }

        } catch (IOException ex) {
            System.out.println("Erro ao enviar pedido reset : " + ex.getMessage());
        }

        return false;

    }
    
    public List<Maquina> maquinasMonitorizadas() {
        return new ListarMaquinasMonitorizadasService().maquinasMonitorizadas();
    }
    

}
