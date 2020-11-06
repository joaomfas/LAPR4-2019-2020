package eapli.base.smm.application;

import eapli.base.smm.server.SmmServer;
import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.gestaomensagens.dominio.Codes;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.dominio.Versoes;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MonitorizacaoController implements Controller {
    
    private final LinhaProducaoRepository linhaProducaoRepository = PersistenceContext.repositories().linhasProducao();
    
    private final BroadCastService broadCastService = new BroadCastService();
    
    public void iniciarMonitorizacao() {
        
        SmmServer smmServer = new SmmServer();
        
        Iterator<LinhaProducao> linhasProducao = linhaProducaoRepository.findAll().iterator();
        
        //inicia as threads por linha e calcula o numero total de máquinas existentes no sistema
        while(linhasProducao.hasNext()) {
            LinhaProducao lp = linhasProducao.next();
            smmServer.monitorizarLinhaProducao(lp);
        }
        
    }
    
    public void encontrarMaquinas(String broadCastAddr) {
        
        SmmServer smmServer = new SmmServer();
        Mensagem mensagem = new Mensagem(Versoes.VERS_0.getVersao(), Codes.HELLO.getCode(), 0, 0, "");
        Map<InetAddress, Mensagem> respostas = broadCastService.send(broadCastAddr, SmmServer.PORTA, mensagem);
        Set<InetAddress> ipKeys = respostas.keySet();
        
        for(InetAddress ip : ipKeys) {            
            smmServer.bindIp(ip, respostas.get(ip).id());
        }
        
    }
    
}
