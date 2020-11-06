package eapli.base.app.backoffice.presentation.materiasprimas;

import eapli.base.gestaomateriasprimas.application.RegistarCategoriaMateriaPrimaController;
import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class RegistarCategoriaMateriaPrimaUI extends AbstractUI{
    private final RegistarCategoriaMateriaPrimaController theController = new RegistarCategoriaMateriaPrimaController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final String codigoCategoria = Console.readLine("Código Categoria:");
        final String descricao = Console.readLine("Descrição:");

        try {
            this.theController.registarCategoriaMateriaPrima(codigoCategoria, descricao);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro ao registar categoria de matéria prima!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Regitar Categoria de Matéria Prima";
    }
}
