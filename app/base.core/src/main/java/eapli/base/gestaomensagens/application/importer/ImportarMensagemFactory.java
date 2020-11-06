package eapli.base.gestaomensagens.application.importer;


public class ImportarMensagemFactory {
    public final IMensagemImporter build(TipoFicheiroMensagemImporter tipo){
        
        switch (tipo) {
        case CSV:
            return new TxtMensagemImporter();
        }
        throw new IllegalStateException("Formato desconhecido!");
    }
}
