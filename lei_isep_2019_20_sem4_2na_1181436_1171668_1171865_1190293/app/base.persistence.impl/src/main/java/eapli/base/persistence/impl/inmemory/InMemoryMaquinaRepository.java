package eapli.base.persistence.impl.inmemory;

import eapli.base.gestaomaquinas.domain.CodInterno;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.domain.MaquinaConfigFile;
import eapli.base.gestaomaquinas.domain.NumeroIdentificacaoUnico;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryMaquinaRepository extends InMemoryDomainRepository<CodInterno, Maquina> 
        implements MaquinaRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Maquina> maquinaComCodigoUnico(NumeroIdentificacaoUnico codigoUnico) {
        return match(e -> e.numeroIdentificacaoUnico().equals(codigoUnico));
    }

    @Override
    public Iterable<MaquinaConfigFile> fichParaEnvioDeMaquina(NumeroIdentificacaoUnico codigoUnico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
