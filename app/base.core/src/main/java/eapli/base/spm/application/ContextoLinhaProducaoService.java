package eapli.base.spm.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.domain.Sequencia;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import eapli.base.spm.application.processamento.ProcessaMensagemBuilder;
import eapli.base.spm.application.processamento.ProcessaMensagem;
import java.util.*;


public class ContextoLinhaProducaoService {
    
    private final MensagemRepository msgRepository = PersistenceContext.repositories().mensagens();
    
    
    public Map<Sequencia, OrdemProducao> contextoAtualLinhaProducao(LinhaProducao linhaProducao, Date instante) {
        
        Map<Sequencia, OrdemProducao> contexto = new HashMap();
        
        Iterator<Mensagem> mensagemComOrdemProd = msgRepository.mensagensComOrdemProducao(linhaProducao, instante).iterator();
        
        while(mensagemComOrdemProd.hasNext()) {
            try {
                Mensagem msg = mensagemComOrdemProd.next();
                
                ProcessaMensagem data = ProcessaMensagemBuilder.build(msg);
                
                Maquina maquina = data.maquina();
                
                for(Sequencia seq : linhaProducao.listaSequencia()) {
                    if(seq.maquina().equals(maquina)) {
                        if(!contexto.containsKey(seq)) {
                            contexto.put(seq, msg.obterOrdemProducao());
                        }
                    }   
                }
                
                if(linhaProducao.listaSequencia().size()==contexto.keySet().size())
                    return contexto;
                
            } catch (SPMException ex) {
            }
        }       
        return contexto;
    }
}