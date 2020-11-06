package eapli.base.app.backoffice.presentation.produtos;

import eapli.base.gestaochaofabrica.export.application.ExportDepositosController;
import eapli.base.gestaoprodutos.application.RegistarProdutoController;
import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;


public class RegistarProdutoUI extends AbstractUI {

    private final RegistarProdutoController theController = new RegistarProdutoController();

    final ExportDepositosController exportDepositosController = new ExportDepositosController();
    
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final String codigoFabrico = Console.readLine("Código Fabrico:");
        final String codigoComercial = Console.readLine("Código Comercial:");
        final String descBreve = Console.readLine("Descrição Breve:");
        final String descCompleta = Console.readLine("Descrição Completa:");
        final String catProduto = Console.readLine("Categoria do Produto:");
        final String unidadeMedida = Console.readLine("Unidade de Medida:");

        try {
            this.theController.registarProduto(codigoFabrico, codigoComercial, descBreve, 
                    descCompleta, catProduto, unidadeMedida);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro ao registar produto!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Regitar Produto";
    }

}
