package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author mdias
 */
public class XmlMateriaPrimaExporter implements ChaoFabricaExporter<MateriaPrima> {

    private PrintWriter stream;

    @Override
    public void begin(String filename) throws IOException {
        stream = new PrintWriter(new FileWriter(filename));
        stream.println("<MateriasPrimas>");
    }

    @Override
    public void element(MateriaPrima mp, Date dataI, Date dataF) {
        stream.println("<MateriaPrima>");
        stream.printf("<CodigoInterno>%s</CodigoInterno>%n", mp.codigo());
        stream.printf("<CodigoCategoria>%s</CodigoCategoria>%n", mp.categoriaMP().identity());
        stream.printf("<Descricao>%s</Descricao>%n", mp.descricao());
        stream.printf("<FichaTecnica src=\"%s\"/>%n", "./" + mp.fichaTecnica().nomeFicheiro());
        stream.printf("<UnidadeMedida>%s</UnidadeMedida>%n", mp.unidadeMedida());
        stream.println("</MateriaPrima>");
    }

    @Override
    public void elementSeparator() {
        // nothing to do
    }

    @Override
    public void end() {
        stream.println("</MateriasPrimas>");
        stream.close();
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            stream.close();
        }
    }
}
