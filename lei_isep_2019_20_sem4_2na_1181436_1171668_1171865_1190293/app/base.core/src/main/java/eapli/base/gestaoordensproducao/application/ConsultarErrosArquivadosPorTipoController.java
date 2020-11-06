package eapli.base.gestaoordensproducao.application;

import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.gestaoordensproducao.repositories.ErroProcessamentoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.EnumErros;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.Arrays;
import java.util.Date;


public class ConsultarErrosArquivadosPorTipoController implements Controller{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ErroProcessamentoRepository repo = PersistenceContext.repositories()
            .erroProcessamento();
    
    public Iterable<EnumErros> tiposErros() {
        return Arrays.asList(EnumErros.values());
    }
    
    public Iterable<ErroProcessamento> errosPorTipo(EnumErros tipo){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return repo.errosArquivadosPorTipo(tipo);
    }
    
    public Iterable<ErroProcessamento> errosEntreDatas(Date dataI, Date dataF){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return repo.errosArquivadosEntreDatas(dataI, dataF);
    }
}
