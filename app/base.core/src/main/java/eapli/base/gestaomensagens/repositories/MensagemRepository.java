package eapli.base.gestaomensagens.repositories;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.framework.domain.repositories.DomainRepository;
import java.util.Date;

public interface MensagemRepository extends DomainRepository<Long, Mensagem> {

    Iterable<Mensagem> mensagensNaoProcessadas();
    
    Iterable<Mensagem> mensagensSemLinhaProducao(int maxResultados);

    Iterable<Mensagem> mensagensSemOrdemProducao(LinhaProducao linhaProducao, int maxResultados);
    
    Iterable<Mensagem> mensagensComOrdemProducao(LinhaProducao linhaProducao, Date dataLimite);

}
