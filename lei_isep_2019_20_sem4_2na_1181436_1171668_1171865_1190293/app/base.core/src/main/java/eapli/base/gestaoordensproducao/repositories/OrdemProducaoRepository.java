/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoordensproducao.repositories;

import eapli.base.gestaoordensproducao.domain.CodigoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.Encomenda;
import eapli.base.gestaoordensproducao.domain.EstadoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.framework.domain.repositories.DomainRepository;

/**
 *
 * @author joaoferreira
 */
public interface OrdemProducaoRepository extends DomainRepository<CodigoOrdemProducao, OrdemProducao> {

    /**
     * Retorna produtos sem ficha de produção definida
     */
    Iterable<OrdemProducao> ordensProducaoComEstado(EstadoOrdemProducao estado);

    /**
     * 
     * @param encomenda
     * @return lista de ordens de producao de uma determindada encomenda
     */
    Iterable<OrdemProducao> ordensProducaoDeEncomenda(Encomenda encomenda);
    
}
