package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.base.gestaoordensproducao.repositories.ErroProcessamentoRepository;
import eapli.base.gestaoordensproducao.repositories.ExecucaoOrdemProducaoRepository;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public ProdutoRepository produtos() {
        return new JpaProdutoRepository();
    }

    @Override
    public LinhaProducaoRepository linhasProducao() {
        return new JpaLinhaProducaoRepository();
    }

    @Override
    public MaquinaRepository maquinas() {
        return new JpaMaquinaRepository();
    }

    @Override
    public MateriaPrimaRepository materiasPrimas() {
        return new JpaMateriaPrimaRepository();
    }

    @Override
    public CategoriaMateriaPrimaRepository categoriasMateriasPrimas() {
        return new JpaCategoriaMateriaPrimaRepository();
    }

    @Override
    public DepositoRepository depositos() {
        return new JpaDepositoRepository();
    }

    @Override
    public OrdemProducaoRepository ordensProducao() {
        return new JpaOrdemProducaoRepository();
    }

    @Override
    public ExecucaoOrdemProducaoRepository execucaoOrdensProducao() {
        return new JpaExecucaoOrdemProducaoRepository();
    }

    @Override
    public MensagemRepository mensagens() {
        return new JpaMensagemRepository();
    }

    @Override
    public ErroProcessamentoRepository erroProcessamento() {
        return new JpaErroProcessamentoRepository();
    }

}
