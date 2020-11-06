package eapli.base.gestaomaquinas.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.domain.Sequencia;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.gestaomaquinas.domain.CodInterno;
import eapli.base.gestaomaquinas.domain.*;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class EspecificarMaquinaController implements Controller {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListarLinhaProducaoService svc
            = new ListarLinhaProducaoService();
    private final MaquinaRepository maquinaRepository 
            = PersistenceContext.repositories().maquinas();
    private final LinhaProducaoRepository linhaProducaoRepository 
            = PersistenceContext.repositories().linhasProducao();
    
    public Maquina registerMaquina(final String idLinhaProducao, Long numSquenciaMaquina,
            final String numeroIdentificacaoUnico,
            final String codInterno, final String descricao, final String numserie, 
            final String datainstalacao, final String marca, final String modelo){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.GESTOR_CHAO_FABRICA);

        final Maquina maquina = new Maquina(new CodInterno(codInterno), new Descricao(descricao), 
                new NumSerie(numserie), new DataInstalacao(datainstalacao), 
                new Marca(marca), new Modelo(modelo), new NumeroIdentificacaoUnico(numeroIdentificacaoUnico));
        
        final LinhaProducao linhaProducao = this.linhaProducaoRepository.ofIdentity(idLinhaProducao).orElseThrow(IllegalStateException::new);;
        
        final TransactionalContext ctx = PersistenceContext.repositories()
                .newTransactionalContext();
        
        try {
            linhaProducao.adicionarMaquina(new Sequencia(numSquenciaMaquina, maquina));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        //ctx.beginTransaction();
        //this.maquinaRepository.save(maquina);
        
        this.linhaProducaoRepository.save(linhaProducao);
        //ctx.commit();
        return maquina;
    }
    
    public Iterable<LinhaProducao> linhasProducao() {
        return this.svc.todasLinhas();
        
    }
    
}
