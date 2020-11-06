package eapli.base.persistence.impl.inmemory;


import eapli.base.gestaoordensproducao.domain.CodigoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.Encomenda;
import eapli.base.gestaoordensproducao.domain.EstadoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.HashSet;
import java.util.Set;


public class InMemoryOrdemProducaoRepository extends InMemoryDomainRepository<CodigoOrdemProducao, OrdemProducao>
        implements OrdemProducaoRepository{
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<OrdemProducao> ordensProducaoComEstado(EstadoOrdemProducao estado) {
        return match(e -> e.estado().equals(estado));
    }

    @Override
    public Iterable<OrdemProducao> ordensProducaoDeEncomenda(Encomenda encomenda) {
        return match(e -> e.encomendas().contains(encomenda));
    }
}
