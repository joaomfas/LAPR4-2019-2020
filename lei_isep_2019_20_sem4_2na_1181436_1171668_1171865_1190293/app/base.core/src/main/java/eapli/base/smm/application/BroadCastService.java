package eapli.base.smm.application;

import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.gestaomensagens.dominio.*;
import eapli.base.infrastructure.persistence.PersistenceContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class BroadCastService {
    
    private final MaquinaRepository maquinaRepository = PersistenceContext.repositories().maquinas();
    
    public Map<InetAddress, Mensagem> send(String broadCastAddr, int porta, Mensagem mensagem) {
        
        Map<InetAddress, Mensagem> respostas = new HashMap();
        
        int respostasEsperadas = (int) maquinaRepository.count();
        
        InetAddress broadCastIP;
        try {
            broadCastIP = InetAddress.getByName(broadCastAddr);
            respostas = new ComunicacaoUDPService(broadCastIP, porta, 2000).enviaMensagem(mensagem, respostasEsperadas);
        } catch (UnknownHostException ex) {
        }
        
        return respostas;
        
    }
    
}
