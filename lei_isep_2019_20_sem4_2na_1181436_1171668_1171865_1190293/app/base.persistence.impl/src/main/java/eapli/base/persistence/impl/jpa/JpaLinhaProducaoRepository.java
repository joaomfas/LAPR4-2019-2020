package eapli.base.persistence.impl.jpa;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.gestaomaquinas.domain.CodInterno;
import javax.persistence.TypedQuery;


public class JpaLinhaProducaoRepository extends BaseJpaRepositoryBase<LinhaProducao, String, String> implements LinhaProducaoRepository{
    
    public JpaLinhaProducaoRepository() {
        super("id");
        
    }

    @Override
    public Iterable<LinhaProducao> linhaProducaoMaquina(CodInterno codInterno) {
        final TypedQuery<LinhaProducao> q = createQuery(
                "SELECT lp FROM LinhaProducao lp join lp.listaSequencia ls "
                    + "join ls.maquina m "
                    + "Where m.codInterno=:codInterno"
                , LinhaProducao.class);
        q.setParameter("codInterno", codInterno);
        return q.getResultList();        
    }
}
