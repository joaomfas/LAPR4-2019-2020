package eapli.base.spm.application;

import eapli.base.spm.application.processamento.S0;
import eapli.base.spm.application.processamento.ProcessaMensagem;
import eapli.base.spm.application.processamento.ProcessaMensagemBuilder;
import eapli.base.spm.application.processamento.Tipo;
import eapli.base.gestaolinhasproducao.domain.*;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import eapli.framework.domain.repositories.TransactionalContext;
import java.util.Iterator;
import java.util.Map;

public class ProcessamentoRunnable implements Runnable {

    private final TransactionalContext txCtx = PersistenceContext.repositories()
            .newTransactionalContext();
    private final MensagemRepository msgRepository = PersistenceContext.repositories().mensagens();
    private final LinhaProducao linhaProducao;
    private final int mensagensAProcessarPorIntervalo;

    public ProcessamentoRunnable(LinhaProducao linhaProducao, int mensagensAProcessarPorIntervalo) {
        this.linhaProducao = linhaProducao;
        this.mensagensAProcessarPorIntervalo = mensagensAProcessarPorIntervalo;
    }

    @Override
    public synchronized void run() {

        Iterator<Mensagem> mensagens = msgRepository.mensagensSemOrdemProducao(linhaProducao, mensagensAProcessarPorIntervalo).iterator();
        txCtx.beginTransaction();
        while (mensagens.hasNext()) {
            Mensagem msg = mensagens.next();
            try {
                ProcessaMensagem data = ProcessaMensagemBuilder.build(msg);

                if (data.tipo() == Tipo.S0 && linhaProducao.listaSequencia().get(0).maquina().equals(data.maquina())) {
                    // primeira máquina da linha, conhece conhece ordem de produção!
                    S0 s0Data = (S0) data;
                    msg.definirOrdemProducao(s0Data.ordemProducao());
                    //                            definirOrdemProducao(msg, s0Data.ordemProducao());
                } else if (data.tipo() == Tipo.S0) {
                    // Inicio de atividade numa maquina que não conhece a ordem de produção
                    // Ordem produção = ultima ordemprodução da maquina na posição anterior
                    Map<Sequencia, OrdemProducao> contexto = new ContextoLinhaProducaoService().contextoAtualLinhaProducao(linhaProducao, msg.dataGeracao());
                    Sequencia seqAnterior = linhaProducao.listaSequencia().get(0);
                    for (int j = 1; j < linhaProducao.listaSequencia().size(); j++) {
                        Sequencia seq = linhaProducao.listaSequencia().get(j);
                        if (seq.maquina().equals(data.maquina())) {
                            //definirOrdemProducao(msg, contexto.get(seqAnterior));
                            msg.definirOrdemProducao(contexto.get(seqAnterior));
                        }
                        seqAnterior = seq;
                    }
                } else {
                    //OrdemProducão = ultima ordem produção para esta maquina!
                    Map<Sequencia, OrdemProducao> contexto = new ContextoLinhaProducaoService().contextoAtualLinhaProducao(linhaProducao, msg.dataGeracao());
                    for (Sequencia seq : contexto.keySet()) {
                        if (seq.maquina().equals(data.maquina())) {
                            //definirOrdemProducao(msg, contexto.get(seq));
                            msg.definirOrdemProducao(contexto.get(seq));
                        }
                    }
                }

                data.processa();
                data.mensagem().marcarComoProcessada();
                msgRepository.save(data.mensagem());
                
            } catch (SPMException ex) {
                new RegistarErrosService().RegistarErro(ex, msg);
            }
        }
        txCtx.commit();
    }
    
    
//    public synchronized void processaMsg(ProcessaMensagem data) throws SPMException {
//        data.processa();
//        data.mensagem().marcarComoProcessada();
//        msgRepository.save(data.mensagem());
//    }
    

}
