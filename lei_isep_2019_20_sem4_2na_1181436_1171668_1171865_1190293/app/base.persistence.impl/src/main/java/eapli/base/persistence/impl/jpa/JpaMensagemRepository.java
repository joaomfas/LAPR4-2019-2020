package eapli.base.persistence.impl.jpa;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import java.util.Date;
import javax.persistence.TypedQuery;

public class JpaMensagemRepository extends BaseJpaRepositoryBase<Mensagem, Long, Long> 
        implements MensagemRepository {
    
    public JpaMensagemRepository() {
        super("codId");
    }

    @Override
    public Iterable<Mensagem> mensagensNaoProcessadas() {
        return match("e.mensagemProcessada=false");
    }

    @Override
    public Iterable<Mensagem> mensagensSemLinhaProducao(int maxResultados) {
        final TypedQuery<Mensagem> q = createQuery(
                "SELECT e FROM Mensagem e "
                    + "WHERE e.linhaProducao=null "
                    + "ORDER BY e.dataGeracao"
                , Mensagem.class);
        q.setMaxResults(maxResultados);
        return q.getResultList();
    }

    @Override
    public Iterable<Mensagem> mensagensSemOrdemProducao(LinhaProducao linhaProducao, int maxResultados) {
        final TypedQuery<Mensagem> q = createQuery(
                "SELECT e FROM Mensagem e "
                    + "WHERE e.linhaProducao=:linhaProducao "
                    + "AND e.ordemProducao=null "
                    + "ORDER BY e.dataGeracao"
                , Mensagem.class);
        q.setParameter("linhaProducao", linhaProducao);
        q.setMaxResults(maxResultados);
        return q.getResultList();
    }

    @Override
    public Iterable<Mensagem> mensagensComOrdemProducao(LinhaProducao linhaProducao, Date dataLimite) {
        final TypedQuery<Mensagem> q = createQuery(
                "SELECT e FROM Mensagem e "
                    + "WHERE e.linhaProducao=:linhaProducao "
                    + "AND e.dataGeracao <= :dataLimite "
                    + "AND e.ordemProducao <> null "
                    + "ORDER BY e.dataGeracao desc"
                , Mensagem.class);
        q.setParameter("linhaProducao", linhaProducao);
        q.setParameter("dataLimite", dataLimite);
        return q.getResultList();
    }

    
    
}
