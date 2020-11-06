package eapli.base.persistence.impl.jpa;

import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;

public class JpaProdutoRepository extends BaseJpaRepositoryBase<Produto, CodigoFabrico, CodigoFabrico> 
        implements ProdutoRepository{

    public JpaProdutoRepository() {
        super("codigoFabrico");
    }

    @Override
    public Iterable<Produto> produtosSemFichaProducao() {
        return match("e.hasFichaProducao=false");
    }
}
