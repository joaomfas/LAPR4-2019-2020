package eapli.base.infrastructure.bootstrapers;

import eapli.base.gestaomaquinas.application.EspecificarMaquinaController;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class MaquinaBootstrapperBase {
    final EspecificarMaquinaController especificarMaquinaController = new EspecificarMaquinaController();

    public MaquinaBootstrapperBase() {
        super();
    }

    protected Maquina registarMaquina(final String idLinhaProducao,Long numSquenciaMaquina,
            final String numIdUnico,
            final String codInterno, final String descricao, final String numserie, 
            final String datainstalacao, final String marca, final String modelo) {
        
        Maquina u = null;
        try {
            u = especificarMaquinaController.registerMaquina(idLinhaProducao, numSquenciaMaquina,
            numIdUnico,
            codInterno, descricao, numserie, 
            datainstalacao,  marca,  modelo);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println(e.getMessage());
        }
        return u;
    } 
}