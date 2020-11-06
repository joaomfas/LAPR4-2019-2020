package eapli.base.infrastructure.bootstrapers;

import eapli.base.gestaomateriasprimas.application.RegistarMateriaPrimaController;
import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MateriasPrimasBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    final RegistarMateriaPrimaController registarMateriaPrimaController = new RegistarMateriaPrimaController();
    final CategoriaMateriaPrimaRepository catMPrepository = PersistenceContext.repositories().categoriasMateriasPrimas();
    public MateriasPrimasBootstrapperBase() {
        super();
    }

    protected MateriaPrima registarMateriaPrima(final String codigo, final CategoriaMateriaPrima categoriaMP, 
            final String descricao, final String fichaTecnicaPath, final String unidadeMedida) {
        MateriaPrima u = null;
        try {
            u = registarMateriaPrimaController.registarMateriaPrima(codigo, categoriaMP, descricao,
                    fichaTecnicaPath, unidadeMedida);
            LOGGER.debug("»»» Matéria Prima - Código interno: %s", codigo);
        } catch (final IntegrityViolationException | ConcurrencyException e) {

        }
        return u;
    }
}
