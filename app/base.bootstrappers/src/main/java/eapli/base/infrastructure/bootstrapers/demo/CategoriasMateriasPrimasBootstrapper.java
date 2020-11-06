package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.CategoriasMateriasPrimasBootstrapperBase;
import eapli.framework.actions.Action;

public class CategoriasMateriasPrimasBootstrapper extends CategoriasMateriasPrimasBootstrapperBase implements Action{
    @Override
    public boolean execute() {
        registarCategoriaMateriaPrima("1001","Metal");
        registarCategoriaMateriaPrima("1002","Madeira");
        registarCategoriaMateriaPrima("1003","Verniz");
        registarCategoriaMateriaPrima("1004","Textil");
        registarCategoriaMateriaPrima("1005","Tinta");
        registarCategoriaMateriaPrima("1006","Vidro");
        registarCategoriaMateriaPrima("1007","Cortiça");
        return true;
    }
}
