package eapli.base.app.scm.console.gestaomensagens;

import eapli.base.gestaomensagens.application.ImportarMensagensTxtController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

public class ImportarMensagensUI extends AbstractUI {

    private final ImportarMensagensTxtController theController = new ImportarMensagensTxtController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        try {
            this.theController.importarMensagensTxt();
        } catch (@SuppressWarnings("unused") final Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        System.out.println("Ficheiros analisados com sucesso!");
        return false;
    }

    @Override
    public String headline() {
        return "Importar Mensagens de Ficheiros";
    }

}
