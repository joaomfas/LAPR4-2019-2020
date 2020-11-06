package eapli.base.gestaomensagens.application;

import eapli.base.gestaomaquinas.domain.CodInterno;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.gestaomateriasprimas.domain.CodigoInterno;
import eapli.base.gestaomensagens.application.importer.IMensagemImporter;
import eapli.base.gestaomensagens.application.importer.ImportarMensagemFactory;
import eapli.base.gestaomensagens.application.importer.ImportarMensagemService;
import eapli.base.gestaomensagens.application.importer.TipoFicheiroMensagemImporter;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.IntegrityViolationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportarMensagensRunnable implements Runnable, ICarregamentoMensagemImporter {

    private final MensagemRepository repository = PersistenceContext.repositories().mensagens();
    private final MaquinaRepository maquinaRepo = PersistenceContext.repositories().maquinas();
    private final ImportarMensagemFactory factory = new ImportarMensagemFactory();
    private final ImportarMensagemService importTxt = new ImportarMensagemService();

    private File ficheiroImportar;

    public ImportarMensagensRunnable() {
    }

    @Override
    public void run() {
        final IMensagemImporter importer = factory.build(TipoFicheiroMensagemImporter.CSV);
        try {
            Iterable<Mensagem> msgs = importTxt.importar(ficheiroImportar.getAbsolutePath(), importer);
            
            for (Mensagem msg : msgs) {
                String[] p = msg.rawData().toString().split(";");

                if (maquinaRepo.containsOfIdentity(new CodInterno(p[0]))) {
                    repository.save(msg);
                }else{
                    throw new IntegrityViolationException("Máquina " + p[0] + " não existe no sistema!");
                }
            }

            ficheiroImportar.renameTo(new File(System.getProperty("user.home") + "/MensagesProcessadas/" + ficheiroImportar.getName()));
        } catch (IOException ex) {
            Logger.getLogger(ImportarMensagensRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void receiveParameter(Object param) {
        this.ficheiroImportar = (File) param;
    }
}
