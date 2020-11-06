package eapli.base.persistence.impl.inmemory;

import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.repositories.ExecucaoOrdemProducaoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryExecucaoOrdemProducaoRepository extends InMemoryDomainRepository<Long, ExecucaoOrdemProducao>
        implements ExecucaoOrdemProducaoRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<ExecucaoOrdemProducao> execucaoDaOrdensProducao(OrdemProducao ordProducao) {
        return match(e -> e.ordemProducao().equals(ordProducao));
    }
}
