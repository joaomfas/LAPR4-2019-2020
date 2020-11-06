package eapli.base.persistence.impl.jpa;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.gestaoordensproducao.domain.EstadoErro;
import eapli.base.gestaoordensproducao.repositories.ErroProcessamentoRepository;
import eapli.base.spm.application.processamento.validacao.exceptions.EnumErros;
import java.util.Date;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

public class JpaErroProcessamentoRepository extends BaseJpaRepositoryBase<ErroProcessamento, Long, Long>
        implements ErroProcessamentoRepository {

    JpaErroProcessamentoRepository() {
        super("id");
    }

    @Override
    public Iterable<ErroProcessamento> errosArquivadosPorTipo(EnumErros tipo) {
        final TypedQuery<ErroProcessamento> q = createQuery(
                "SELECT e FROM ErroProcessamento e WHERE e.tipo = :tipo "
                        + "AND e.estado = :estado",
                ErroProcessamento.class);
        q.setParameter("tipo", tipo);
        q.setParameter("estado", EstadoErro.ARQUIVADO);
        return q.getResultList();
    }

    @Override
    public Iterable<ErroProcessamento> errosArquivadosEntreDatas(Date dataInicio, Date dataFim) {
        final TypedQuery<ErroProcessamento> q = createQuery(
                "SELECT e FROM ErroProcessamento e WHERE e.dataGeracao BETWEEN :dataInicio AND :dataFim "
                        + "AND e.estado = :estado",
                ErroProcessamento.class);
        q.setParameter("dataInicio", dataInicio, TemporalType.DATE);
        q.setParameter("dataFim", dataFim, TemporalType.DATE);
        q.setParameter("estado", EstadoErro.ARQUIVADO);
        return q.getResultList();
    }

    
    @Override
    public Iterable<ErroProcessamento> errosPendentes() {
        
        final TypedQuery<ErroProcessamento> q = createQuery(
                "SELECT e FROM ErroProcessamento e " +
                    "WHERE e.estado=:estado "
                , ErroProcessamento.class);
        q.setParameter("estado", EstadoErro.PENDENTE);
        return q.getResultList();

    }
    
    @Override
    public Iterable<ErroProcessamento> errosPendentesPorLinha(LinhaProducao linhaProducao) {
        
        final TypedQuery<ErroProcessamento> q = createQuery(
                "SELECT e FROM ErroProcessamento e, Mensagem m " +
                    "WHERE e.mensagem=m " +
                    "AND linhaproducao_id=:linhaProducao " + 
                    "AND e.estado=:estado ",
                ErroProcessamento.class);
        q.setParameter("linhaProducao", linhaProducao.identity());
        q.setParameter("estado", EstadoErro.PENDENTE);
        return q.getResultList();

    }

}
