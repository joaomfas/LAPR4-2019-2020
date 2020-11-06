/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoprodutos.repositories;

import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.framework.domain.repositories.DomainRepository;

/**
 *
 * @author joaoferreira
 */
public interface ProdutoRepository extends DomainRepository<CodigoFabrico, Produto> {
    
    /**
     * Retorna produtos sem ficha de produção definida
     */
    Iterable<Produto> produtosSemFichaProducao();
}
