package eapli.base.gestaomaquinas.application;

import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.domain.MaquinaConfigFile;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.ArrayList;

public class MarcarFicheiroParaEnvioController implements Controller {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final MaquinaRepository maquinaRepository = PersistenceContext.repositories().maquinas();
    private final ListarMaquinasService svc = new ListarMaquinasService();

    public Iterable<Maquina> todasMaquinas() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return svc.todasMaquinas();
    }
    
    public void marcarFichParaEnvio(Maquina mq, MaquinaConfigFile file){
        file.toggleMarcarParaEnvio();
        mq.setFichParaEnvio(file.toString());
        maquinaRepository.save(mq);
    }
}
