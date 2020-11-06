package eapli.base.gestaomensagens.application;

public class CarregarMensagemFactory {

    public final ICarregamentoMensagemImporter build(ModoCarregamentoMensagens tipo) {

        switch (tipo) {
            case FICHEIRO:
                return new ImportarMensagensRunnable();
            case SERVIDOR:
                return new SCMServerThreadLinha();
        }
        throw new IllegalStateException("Formato desconhecido!");
    }
}
