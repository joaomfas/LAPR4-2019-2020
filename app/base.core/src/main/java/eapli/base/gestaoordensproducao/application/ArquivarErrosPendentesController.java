package eapli.base.gestaoordensproducao.application;

import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.gestaoordensproducao.domain.EstadoErro;
import eapli.base.gestaoordensproducao.repositories.ErroProcessamentoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.List;

public class ArquivarErrosPendentesController implements Controller {
    
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ErroProcessamentoRepository repo = PersistenceContext.repositories()
            .erroProcessamento();
    private final TransactionalContext txCtx = PersistenceContext.repositories()
            .newTransactionalContext();
    
    public boolean arquivarErrosPendentes(List<ErroProcessamento> errosParaArquivar) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        
        boolean success = true;
        // explicitly begin a transaction
        txCtx.beginTransaction();
        for(ErroProcessamento erro : errosParaArquivar) {
            //erro já estava arquivado
            if(erro.estado() == EstadoErro.ARQUIVADO) {
                success = false;
                txCtx.rollback();
            }
            erro.arquivarErro();
            repo.save(erro);
        }
        // explicitly begin a transaction
        txCtx.commit();
        
        return success;
    }
    
}
