package eapli.base.persistence.impl.inmemory;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.gestaoordensproducao.domain.EstadoErro;
import eapli.base.gestaoordensproducao.repositories.ErroProcessamentoRepository;
import eapli.base.spm.application.processamento.validacao.exceptions.EnumErros;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.Date;


public class InMemoryErroProcessamentoRepository extends InMemoryDomainRepository<Long, ErroProcessamento> 
    implements ErroProcessamentoRepository {
    
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<ErroProcessamento> errosArquivadosPorTipo(EnumErros tipo) {
        return match(e -> e.tipoErro().equals(tipo) && e.estado().equals(EstadoErro.ARQUIVADO));
    }

    @Override
    public Iterable<ErroProcessamento> errosArquivadosEntreDatas(Date dataInicio, Date dataFim) {
        return match(e -> e.estado().equals(EstadoErro.ARQUIVADO) && e.dataErro().after(dataFim) && e.dataErro().before(dataFim));
    }

    @Override
    public Iterable<ErroProcessamento> errosPendentes() {
        return match(e -> e.estado().equals(EstadoErro.PENDENTE));
    }

    @Override
    public Iterable<ErroProcessamento> errosPendentesPorLinha(LinhaProducao linhaProducao) {
        return match(e -> e.estado().equals(EstadoErro.PENDENTE) && e.mensagem().obterLinhaProducao().equals(linhaProducao));
    }

    
}
