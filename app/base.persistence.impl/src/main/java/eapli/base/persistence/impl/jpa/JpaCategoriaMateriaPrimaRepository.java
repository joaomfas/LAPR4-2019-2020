package eapli.base.persistence.impl.jpa;

import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.gestaomateriasprimas.domain.CodigoCategoria;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;

public class JpaCategoriaMateriaPrimaRepository extends BaseJpaRepositoryBase<CategoriaMateriaPrima, CodigoCategoria, CodigoCategoria> 
        implements CategoriaMateriaPrimaRepository{
    
    JpaCategoriaMateriaPrimaRepository() {
        super("codigoCategoria");
    }
    
}
