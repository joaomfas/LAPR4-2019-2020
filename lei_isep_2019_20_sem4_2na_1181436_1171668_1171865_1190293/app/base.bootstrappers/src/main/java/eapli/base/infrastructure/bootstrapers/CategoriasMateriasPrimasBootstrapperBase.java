package eapli.base.infrastructure.bootstrapers;

import eapli.base.gestaomateriasprimas.application.RegistarCategoriaMateriaPrimaController;
import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoriasMateriasPrimasBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    final RegistarCategoriaMateriaPrimaController registarCategoriaMateriaPrimaController = new RegistarCategoriaMateriaPrimaController();

    public CategoriasMateriasPrimasBootstrapperBase() {
        super();
    }

    protected CategoriaMateriaPrima registarCategoriaMateriaPrima(final String codigoCategoria, final String descricao) {
        CategoriaMateriaPrima u = null;
        try {
            u = registarCategoriaMateriaPrimaController.registarCategoriaMateriaPrima(codigoCategoria, descricao);
            LOGGER.debug("»»» Categoria Matéria Prima - Código Categoria: %s", codigoCategoria);
        } catch (final IntegrityViolationException | ConcurrencyException e) {

        }
        return u;
    }
}
