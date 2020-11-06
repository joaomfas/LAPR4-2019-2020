package eapli.base.gestaoordensproducao.repositories;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.spm.application.processamento.validacao.exceptions.EnumErros;
import eapli.framework.domain.repositories.DomainRepository;
import java.util.Date;

public interface ErroProcessamentoRepository extends DomainRepository<Long, ErroProcessamento> {
    public Iterable<ErroProcessamento> errosArquivadosPorTipo(EnumErros tipo);
    public Iterable<ErroProcessamento> errosArquivadosEntreDatas(Date dataInicio, Date dataFim);
    
    public Iterable<ErroProcessamento> errosPendentes();
    public Iterable<ErroProcessamento> errosPendentesPorLinha(LinhaProducao linhaProducao);
    
}
