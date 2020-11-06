package eapli.base.gestaodepositos.application;

import eapli.base.gestaodepositos.domain.CodigoDeposito;
import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegistarDepositoController implements Controller {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final DepositoRepository repository = PersistenceContext.repositories().depositos();

    public Deposito registarDeposito(final String codigoDeposito, final String descricao) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_CHAO_FABRICA, BaseRoles.POWER_USER);
        try {
            final Deposito newDeposito = new Deposito(new CodigoDeposito(codigoDeposito), new Descricao(descricao));
            return this.repository.save(newDeposito);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
}
