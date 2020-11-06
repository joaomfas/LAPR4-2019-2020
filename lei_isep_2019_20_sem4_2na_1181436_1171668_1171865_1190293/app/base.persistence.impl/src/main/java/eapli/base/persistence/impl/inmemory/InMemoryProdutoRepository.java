package eapli.base.persistence.impl.inmemory;

import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;


public class InMemoryProdutoRepository extends InMemoryDomainRepository<CodigoFabrico, Produto>
        implements ProdutoRepository  {

   static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Produto> produtosSemFichaProducao() {
        return match(Produto::hasFichaProducao);
    }
}
