package eapli.base.spm.application;

import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import eapli.base.spm.application.*;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.gestaoordensproducao.repositories.ErroProcessamentoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.TransactionalContext;

public class RegistarErrosService implements Controller {

    private final MensagemRepository msgRepository = PersistenceContext.repositories().mensagens();

    private final ErroProcessamentoRepository repository = PersistenceContext.repositories().erroProcessamento();

    public synchronized boolean RegistarErro(SPMException exception, Mensagem msg) {

        msg.marcarComoProcessada();
        msgRepository.save(msg);
        ErroProcessamento erroProcessamento = new ErroProcessamento(exception.name(), msg);
        repository.save(erroProcessamento);

        return true;

    }

}
