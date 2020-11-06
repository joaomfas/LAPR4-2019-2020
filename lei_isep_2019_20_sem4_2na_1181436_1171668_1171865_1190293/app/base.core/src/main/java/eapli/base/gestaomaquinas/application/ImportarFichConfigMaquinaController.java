package eapli.base.gestaomaquinas.application;

import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.fileImporters.MaquinaImportConfigService;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.io.IOException;

public class ImportarFichConfigMaquinaController implements Controller {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final MaquinaRepository maquinaRepository 
            = PersistenceContext.repositories().maquinas();
    private final MaquinaImportConfigService importConfig = new MaquinaImportConfigService();
    public boolean importarFichConfig(final Maquina maquina, final String path, final String descricao) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.GESTOR_CHAO_FABRICA);
        try {
            byte[] fichBytes = importConfig.importar(path);
            maquina.adicionarConfigFile(fichBytes, descricao);
            maquinaRepository.save(maquina);
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
}
