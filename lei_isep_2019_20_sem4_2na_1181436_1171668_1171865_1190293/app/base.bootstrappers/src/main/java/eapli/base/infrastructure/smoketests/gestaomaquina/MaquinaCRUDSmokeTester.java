/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.smoketests.gestaomaquina;

import eapli.base.gestaomaquinas.domain.*;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.utils.DateTime;
import eapli.framework.validations.Invariants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 35193
 */
public class MaquinaCRUDSmokeTester {
    private static final Logger LOGGER = LogManager.getLogger(MaquinaCRUDSmokeTester.class);

    private final MaquinaRepository repo = PersistenceContext.repositories().maquinas();

    public void testMaquinaCRUD() {
        
        // save
        CodInterno codInterno = new CodInterno("10012");
        DataInstalacao dataInstalacao = new DataInstalacao("10/10/2010");
        Descricao descricao = new Descricao("brief description.");
        Marca marca = new Marca("ABC2");
        Modelo modelo = new Modelo("XYZ2");
        NumSerie numSerie = new NumSerie("12l2");
        NumeroIdentificacaoUnico niu1 = new NumeroIdentificacaoUnico("1022");        
        
        repo.save(new Maquina(codInterno, descricao, numSerie, dataInstalacao, marca, modelo, niu1));
        
        CodInterno codInterno2 = new CodInterno("10022");
        DataInstalacao dataInstalacao2 = new DataInstalacao("20/10/2018");
        Descricao descricao2 = new Descricao("brief description.");
        Marca marca2 = new Marca("ABCx");
        Modelo modelo2 = new Modelo("XYZedx");
        NumSerie numSerie2 = new NumSerie("12l9x");
        NumeroIdentificacaoUnico niu2 = new NumeroIdentificacaoUnico("1022");
        
        repo.save(new Maquina(codInterno2, descricao2, numSerie2, dataInstalacao2, marca2, modelo2, niu2));
        
        LOGGER.info("»»» Maquinas criadas");
        
        // findAll
        final Iterable<Maquina> l = repo.findAll();
        Invariants.nonNull(l);
        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» encontrar todos as maquinas");

        // count
        final long n = repo.count();
        LOGGER.info("»»» # maquinas = {}", n);

        // ofIdentity
        
        final Maquina m1 = repo.ofIdentity(new CodInterno("10012")).orElseThrow(IllegalStateException::new);
        final Maquina m2 = repo.ofIdentity(new CodInterno("10022")).orElseThrow(IllegalStateException::new);
        //LOGGER.info("»»» encontrar maquinas através da sua identidade");
        
        // containsOfIdentity
        final boolean hasId = repo.containsOfIdentity(m1.identity());
        Invariants.ensure(hasId);
        LOGGER.info("»»» encontrar maquina que contenha identidade");

        // contains
        final boolean has = repo.contains(m1);
        Invariants.ensure(has);
        LOGGER.info("»»» contém maquina");

        // delete
        repo.delete(m1);
        LOGGER.info("»»» apagar maquina");

        // deleteOfIdentity
        repo.deleteOfIdentity(m2.identity());
        LOGGER.info("»»» apagar maquina que contenha identidade");

        // size
        final long n2 = repo.size();
        Invariants.ensure(n2 == n - 2);
        LOGGER.info("»»» # maquina = {}", n2);
        
        
    }
    
}
