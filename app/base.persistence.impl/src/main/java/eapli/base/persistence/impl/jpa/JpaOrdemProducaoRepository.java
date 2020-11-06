package eapli.base.persistence.impl.jpa;

import eapli.base.gestaoordensproducao.domain.CodigoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.Encomenda;
import eapli.base.gestaoordensproducao.domain.EstadoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.TypedQuery;


public class JpaOrdemProducaoRepository extends BaseJpaRepositoryBase<OrdemProducao, CodigoOrdemProducao, CodigoOrdemProducao> 
        implements OrdemProducaoRepository{

    public JpaOrdemProducaoRepository() {
        super("codigoOrdemProducao");
    }

    @Override
    public Iterable<OrdemProducao> ordensProducaoComEstado(final EstadoOrdemProducao estado) {
        final Map<String, Object> params = new HashMap<>();
        params.put("estado", estado);
        return match ("e.estado = :estado", params);
    }

    @Override
    public Iterable<OrdemProducao> ordensProducaoDeEncomenda(Encomenda encomenda) {       
        final TypedQuery<OrdemProducao> query = entityManager().createQuery(
                "SELECT d FROM OrdemProducao d JOIN d.encomendas aind WHERE aind.codigoEncomenda = :encomenda",
                OrdemProducao.class);
        query.setParameter("encomenda", encomenda.toString());

        return query.getResultList();
    }
}
