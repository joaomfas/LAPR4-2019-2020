package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.gestaomateriasprimas.domain.CodigoCategoria;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.infrastructure.bootstrapers.MateriasPrimasBootstrapperBase;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;

public class MateriasPrimasBootstrapper extends MateriasPrimasBootstrapperBase implements Action{
    
    CategoriaMateriaPrimaRepository catMPRepo = PersistenceContext.repositories().categoriasMateriasPrimas();
    
    @Override
    public boolean execute() {
        registarMateriaPrima("2001",getCatMP("1002"),"Tábua","","un");
        registarMateriaPrima("2002",getCatMP("1003"),"Verniz A","","kg");
        registarMateriaPrima("2003",getCatMP("1001"),"Vara ferro","","kg");
        registarMateriaPrima("2004",getCatMP("1004"),"Tecido almofadado","","m");
        registarMateriaPrima("2005",getCatMP("1006"),"Vidro 5x5","","un");
        registarMateriaPrima("2006",getCatMP("1007"),"Placa cortiça 5x5",System.getProperty("user.home")+"/ft_cortica.pdf","un");
        registarMateriaPrima("32000142",getCatMP("1007"),"MP teste","","un");
        registarMateriaPrima("41000651",getCatMP("1007"),"MP teste 2","","un");
        return true;
    }
    
    private CategoriaMateriaPrima getCatMP(final String acronym) {
        return catMPRepo.ofIdentity(new CodigoCategoria(acronym)).orElseThrow(IllegalStateException::new);
    }
}