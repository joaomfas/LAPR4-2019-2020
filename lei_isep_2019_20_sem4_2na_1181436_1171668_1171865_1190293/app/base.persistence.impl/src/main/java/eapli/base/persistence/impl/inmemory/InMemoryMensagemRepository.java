package eapli.base.persistence.impl.inmemory;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.Date;

public class InMemoryMensagemRepository extends InMemoryDomainRepository<Long, Mensagem>
        implements MensagemRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Mensagem> mensagensNaoProcessadas() {
        return match(Mensagem::mensagemPorProcessar);
    }    

    @Override
    public Iterable<Mensagem> mensagensSemLinhaProducao(int maxResultados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Mensagem> mensagensSemOrdemProducao(LinhaProducao linhaProducao, int maxResultados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Mensagem> mensagensComOrdemProducao(LinhaProducao linhaProducao, Date dataLimite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
