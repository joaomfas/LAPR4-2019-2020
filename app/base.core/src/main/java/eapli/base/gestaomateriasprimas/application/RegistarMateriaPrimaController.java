package eapli.base.gestaomateriasprimas.application;

import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.base.gestaomateriasprimas.domain.CodigoInterno;
import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.gestaomateriasprimas.domain.FichaTecnica;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.base.gestaoprodutos.domain.UnidadeMedida;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegistarMateriaPrimaController implements Controller {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListarCategoriaMateriaPrimaService svc = new ListarCategoriaMateriaPrimaService();
    private final MateriaPrimaRepository repository = PersistenceContext.repositories().materiasPrimas();

    public MateriaPrima registarMateriaPrima(final String codigo, final CategoriaMateriaPrima categoriaMP, final String descricao, final String fichaTecnicaPath, final String unidadeMedida) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);
        try {
            final MateriaPrima newMateriaPrima = new MateriaPrima(new CodigoInterno(codigo), categoriaMP, 
                    new Descricao(descricao), new FichaTecnica(fichaTecnicaPath), new UnidadeMedida(unidadeMedida));
            return this.repository.save(newMateriaPrima);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Iterable<CategoriaMateriaPrima> categoriasMateriasPrimas() {
        return this.svc.allCategoriasMateriasPrimas();
    }

}
