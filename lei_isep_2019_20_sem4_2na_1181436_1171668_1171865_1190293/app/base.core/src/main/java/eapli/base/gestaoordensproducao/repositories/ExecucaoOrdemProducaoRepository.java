/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoordensproducao.repositories;

import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.framework.domain.repositories.DomainRepository;

/**
 *
 * @author joaoferreira
 */
public interface ExecucaoOrdemProducaoRepository extends DomainRepository<Long, ExecucaoOrdemProducao>{
    
    Iterable<ExecucaoOrdemProducao> execucaoDaOrdensProducao(OrdemProducao ordProducao);
    
}
