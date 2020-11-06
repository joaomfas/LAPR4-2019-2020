package eapli.base.persistence.impl.jpa;

import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.repositories.ExecucaoOrdemProducaoRepository;
import java.util.HashMap;
import java.util.Map;

public class JpaExecucaoOrdemProducaoRepository extends BaseJpaRepositoryBase<ExecucaoOrdemProducao, Long, Long>
        implements ExecucaoOrdemProducaoRepository {

    public JpaExecucaoOrdemProducaoRepository() {
        super("numOrdemProducao");
    }

    @Override
    public Iterable<ExecucaoOrdemProducao> execucaoDaOrdensProducao(OrdemProducao ordProducao) {
        final Map<String, Object> params = new HashMap<>();
        params.put("ordemProducao", ordProducao);
        return match("e.ordemProducao = :ordemProducao", params);
    }

}
