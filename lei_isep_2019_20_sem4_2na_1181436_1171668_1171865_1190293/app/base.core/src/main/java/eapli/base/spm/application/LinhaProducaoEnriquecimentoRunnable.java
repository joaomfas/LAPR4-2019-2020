package eapli.base.spm.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import eapli.base.spm.application.processamento.ProcessaMensagemBuilder;
import eapli.base.spm.application.processamento.ProcessaMensagem;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinhaProducaoEnriquecimentoRunnable implements Runnable {

    private final MensagemRepository msgRepository = PersistenceContext.repositories().mensagens();
    private final LinhaProducaoRepository linhaProducaoRepository = PersistenceContext.repositories().linhasProducao();
    private final int mensagensAProcessarPorIntervalo;
    private final int duracaoIntervalo;

    public LinhaProducaoEnriquecimentoRunnable(int mensagensAProcessarPorIntervalo, int duracaoIntervalo) {
        this.mensagensAProcessarPorIntervalo = mensagensAProcessarPorIntervalo;
        this.duracaoIntervalo = duracaoIntervalo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Iterator<Mensagem> mensagens = msgRepository.mensagensSemLinhaProducao(mensagensAProcessarPorIntervalo).iterator();
                while (mensagens.hasNext()) {
                    Mensagem msg = mensagens.next();
                    try {
                        ProcessaMensagem data = ProcessaMensagemBuilder.build(msg);
                        LinhaProducao lp = linhaProducaoRepository.linhaProducaoMaquina(data.maquina().codInterno()).iterator().next();
                        definirLinhaProducao(msg, lp, data.buildCalendar().getTime());

                    } catch (SPMException ex) {
                        new RegistarErrosService().RegistarErro(ex, msg);
                    }
                }
                Thread.sleep(duracaoIntervalo);
            } catch (InterruptedException ex) {
                Logger.getLogger(LinhaProducaoEnriquecimentoRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private synchronized void definirLinhaProducao(Mensagem msg, LinhaProducao lp, Date datageracao) {
        Mensagem ms = msgRepository.ofIdentity(msg.identity()).get();
        ms.definirLinhaProducao(lp);
        ms.updateDataGeracao(datageracao);
        msgRepository.save(ms);
    }

}
