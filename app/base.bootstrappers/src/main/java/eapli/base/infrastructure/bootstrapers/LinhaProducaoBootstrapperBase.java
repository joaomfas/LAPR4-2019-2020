package eapli.base.infrastructure.bootstrapers;

import eapli.base.gestaolinhasproducao.application.RegistarLinhaProducaoController;
import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class LinhaProducaoBootstrapperBase {
    final RegistarLinhaProducaoController registarLinhaProducaoController = new RegistarLinhaProducaoController();

    public LinhaProducaoBootstrapperBase() {
        super();
    }

    protected LinhaProducao registarLinhaProducao(final String id) {
        LinhaProducao u = null;
        try {
            u = registarLinhaProducaoController.registarLinhaProducao(id);
        } catch (final IntegrityViolationException | ConcurrencyException e) {

        }
        return u;
    }
}
