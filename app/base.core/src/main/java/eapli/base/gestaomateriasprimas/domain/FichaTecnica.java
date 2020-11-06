package eapli.base.gestaomateriasprimas.domain;

import eapli.framework.domain.model.ValueObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class FichaTecnica implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String nomeFich;
    @Lob
    @Column(name = "FICHA_TECNICA", columnDefinition = "BLOB")
    private byte[] fichaTecnicaInBytes;

    public FichaTecnica(final String fichaTecnicaPath) throws FileNotFoundException {
        if (fichaTecnicaPath.isEmpty()) {
            this.nomeFich = "";
            this.fichaTecnicaInBytes = null;
        } else {
            readFile(fichaTecnicaPath);
        }
    }

    protected void readFile(final String fichaTecnicaPath) throws FileNotFoundException {
        int index = fichaTecnicaPath.lastIndexOf("/");
        this.nomeFich = fichaTecnicaPath.substring(index + 1, fichaTecnicaPath.length());
        File fichaTecnicaFile = new File(fichaTecnicaPath);
        fichaTecnicaInBytes = new byte[(int) fichaTecnicaFile.length()];
        try {
            FileInputStream fis = new FileInputStream(fichaTecnicaFile);
            fis.read(fichaTecnicaInBytes);
            fis.close();
        } catch (Exception ex) {
            throw new FileNotFoundException("Erro ao carregar ficha técnica!");
        }
    }

    public String nomeFicheiro() {
        return this.nomeFich;
    }
    
    protected FichaTecnica() {
        this.nomeFich = "";
        this.fichaTecnicaInBytes = null;
    }
}
