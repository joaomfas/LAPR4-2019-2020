package eapli.base.gestaomaquinas.repositories;

import eapli.base.gestaomaquinas.domain.CodInterno;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.domain.MaquinaConfigFile;
import eapli.base.gestaomaquinas.domain.NumeroIdentificacaoUnico;
import eapli.framework.domain.repositories.DomainRepository;

public interface MaquinaRepository extends DomainRepository<CodInterno, Maquina>{
    
    Iterable<Maquina> maquinaComCodigoUnico(NumeroIdentificacaoUnico codigoUnico);
    
    Iterable<MaquinaConfigFile> fichParaEnvioDeMaquina(NumeroIdentificacaoUnico codigoUnico);
}
