package eapli.base.infrastructure.bootstrapers;

import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.repositories.ErroProcessamentoRepository;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErroProcessamentoArquivadoBase {
private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    final ErroProcessamentoRepository erroPRepo = PersistenceContext.repositories().erroProcessamento();
    final OrdemProducaoRepository ordemPRepo = PersistenceContext.repositories().ordensProducao();
    final DepositoRepository depositoRepo = PersistenceContext.repositories().depositos();
    
    public ErroProcessamentoArquivadoBase() {
        super();
    }

    protected void registarErroArquivado(final ErroProcessamento erro) {
        try {
           erro.arquivarErro();
           erroPRepo.save(erro);
        } catch (final IntegrityViolationException | ConcurrencyException e) {

        }
    }
    
    protected void registarOrdemProducao(final OrdemProducao ordemP) {
        try {
           ordemPRepo.save(ordemP);
        } catch (final IntegrityViolationException | ConcurrencyException e) {

        }
    }
}
