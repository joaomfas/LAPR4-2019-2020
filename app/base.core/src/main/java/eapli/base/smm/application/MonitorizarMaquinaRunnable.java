package eapli.base.smm.application;

import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomensagens.dominio.Codes;
import eapli.base.gestaomensagens.dominio.Mensagem;
import java.io.IOException;
import java.util.LinkedList;


public class MonitorizarMaquinaRunnable implements Runnable {

    private static final int PORTA = 9999;
    
    private final Maquina maquina;
    
    private LinkedList<Mensagem> mensagensAEnviar;

    private Mensagem ultimaMensagemRecebida;

    public MonitorizarMaquinaRunnable(Maquina maquina) {
        this.maquina = maquina;
        this.mensagensAEnviar = new LinkedList();
        this.ultimaMensagemRecebida = null;
    }

    @Override
    public void run() {
        
        while (mensagensAEnviar.size() > 0) {

            Mensagem msgAEnviar = mensagensAEnviar.removeFirst();
            
            try {
                Mensagem resposta = new ComunicacaoUDPService(maquina.ipAddress(), PORTA, maquina.timeOut()).enviaMensagem(msgAEnviar);

                if (resposta != null) {
                    if (resposta.code() == Codes.ACK.getCode()) {
                        maquina.update();
                    }
                    ultimaMensagemRecebida = resposta;
                }

            } catch (IOException ex) {
            }

        }

    }

    
    public void adicionaMensagem(Mensagem e) {
        mensagensAEnviar.addFirst(e);
    }

    //informação da ultima mensagem recebida
    public Mensagem ultimaMensagemRecebida() {
        return ultimaMensagemRecebida;
    }

    

}
