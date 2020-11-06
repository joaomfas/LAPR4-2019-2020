
package eapli.base.infrastructure.persistence;

import eapli.base.clientusermanagement.repositories.ClientUserRepository;

import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.repositories.ErroProcessamentoRepository;
import eapli.base.gestaoordensproducao.repositories.ExecucaoOrdemProducaoRepository;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

	/**
	 * factory method to create a transactional context to use in the repositories
	 *
	 * @return
	 */
	TransactionalContext newTransactionalContext();

	/**
	 *
	 * @param autoTx the transactional context to enrol
	 * @return
	 */
	UserRepository users(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	UserRepository users();

	/**
	 *
	 * @param autoTx the transactional context to enroll
	 * @return
	 */
	ClientUserRepository clientUsers(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	ClientUserRepository clientUsers();

	/**
	 *
	 * @param autoTx the transactional context to enroll
	 * @return
	 */
	SignupRequestRepository signupRequests(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	SignupRequestRepository signupRequests();
            
        /**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
        ProdutoRepository produtos();
        
        /**
         * repository will be created in auto transaction mode
         * 
         * @return 
         */
        MateriaPrimaRepository materiasPrimas();
        
        /**
         * repository will be created in auto transaction mode
         * 
         * @return 
         */
        CategoriaMateriaPrimaRepository categoriasMateriasPrimas();
   
        /**
         * repository will be created in auto transaction mode
         * 
         * @return 
         */
        LinhaProducaoRepository linhasProducao();
        
        /**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
        MaquinaRepository maquinas();
        
        /**
         * repository will be created in auto transaction mode
         * 
         * @return 
         */
        DepositoRepository depositos();
        
        /**
         * repository will be created in auto transaction mode
         * 
         * @return 
         */
        OrdemProducaoRepository ordensProducao();
        
        /**
         * repository will be created in auto transaction mode
         * 
         * @return 
         */
        ExecucaoOrdemProducaoRepository execucaoOrdensProducao();
        
        
        /**
         * repository will be created in auto transaction mode
         * 
         * @return 
         */
        MensagemRepository mensagens();
        
        
        /**
         * repository will be created in auto transaction mode
         * 
         * @return 
         */
        ErroProcessamentoRepository erroProcessamento();
        
}
