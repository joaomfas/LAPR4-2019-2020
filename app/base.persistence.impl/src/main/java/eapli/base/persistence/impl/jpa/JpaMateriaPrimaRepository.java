package eapli.base.persistence.impl.jpa;

import eapli.base.gestaomateriasprimas.domain.CodigoInterno;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;

public class JpaMateriaPrimaRepository extends BaseJpaRepositoryBase<MateriaPrima, CodigoInterno, CodigoInterno> 
        implements MateriaPrimaRepository{
    
    JpaMateriaPrimaRepository() {
        super("codigo");
    }
    
}
