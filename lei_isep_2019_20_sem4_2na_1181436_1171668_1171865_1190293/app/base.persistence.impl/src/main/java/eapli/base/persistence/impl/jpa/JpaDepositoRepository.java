package eapli.base.persistence.impl.jpa;

import eapli.base.gestaodepositos.domain.CodigoDeposito;
import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaodepositos.repository.DepositoRepository;

public class JpaDepositoRepository extends BaseJpaRepositoryBase<Deposito, CodigoDeposito, CodigoDeposito> 
        implements DepositoRepository{
    
    JpaDepositoRepository() {
        super("codigoDeposito");
    }
    
}
