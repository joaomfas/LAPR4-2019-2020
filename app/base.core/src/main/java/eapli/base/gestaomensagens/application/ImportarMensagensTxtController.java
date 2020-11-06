package eapli.base.gestaomensagens.application;

import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.IntegrityViolationException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportarMensagensTxtController implements Controller {
    
    CarregarMensagemFactory factory = new CarregarMensagemFactory();

    public void importarMensagensTxt() throws InterruptedException {
        File pastaPorProcessar = new File(System.getProperty("user.home") + "/MensagensPorProcessar");
        List<File> listaFicheiros = listarFicheirosEmPasta(pastaPorProcessar);

        if (listaFicheiros.isEmpty()) {
            throw new IntegrityViolationException("Não existem ficheiros a importar!");
        }

        File pastaProcessadas = new File(System.getProperty("user.home") + "/MensagesProcessadas");
        if (!pastaProcessadas.exists()) {
            pastaProcessadas.mkdir();
        }

        List<Thread> listaThreads = new ArrayList<>();

        for (File ficheiro : listaFicheiros) {
            if (ficheiro.getName().charAt(0) != '.') {
                ICarregamentoMensagemImporter r = factory.build(ModoCarregamentoMensagens.FICHEIRO);
                r.receiveParameter(ficheiro);
                try {
                    Thread t = new Thread((Runnable) r);
                    t.start();
                    listaThreads.add(t);
                } catch (Exception e) {
                    throw new IntegrityViolationException(e.getMessage());
                }
            }
        }

        for (Thread t : listaThreads) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                throw new InterruptedException("Erro ao esperar pelo fim da thread!");
            }
        }
    }

    public List<File> listarFicheirosEmPasta(final File pasta) {
        List<File> listaFicheiros = new ArrayList<>();
        for (final File fileEntry : pasta.listFiles()) {
            listaFicheiros.add(fileEntry);
        }
        return listaFicheiros;
    }
}
