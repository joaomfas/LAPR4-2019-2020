package eapli.base.gestaomaquinas.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class MaquinaConfigFile implements ValueObject {
    private static final long serialVersionUID = 1L;
    
    @Lob
    @Column(name = "MAQUINA_CONFIG", columnDefinition = "BLOB")
    private final byte[] fichConfigMaquina;
    
    private final String configDesc;
    
    private boolean marcarParaEnvio;
    
    protected MaquinaConfigFile() {
        this.fichConfigMaquina=null;
        this.configDesc="";
        this.marcarParaEnvio = false;
    }

    public byte[] fichConfigMaquina() {
        return fichConfigMaquina;
    }
    
    public MaquinaConfigFile(byte[] fichBytes, String desc) {
        Preconditions.nonNull(fichBytes, "Ficheiro Vazio!");
        Preconditions.ensure(fichBytes.length>0, "Ficheiro Vazio!");
        Preconditions.ensure(desc.length()<=50, "Descrição com mais do que 50 caracteres!");
        fichConfigMaquina = fichBytes;
        configDesc = desc;
        marcarParaEnvio = false;
    }
    
    public void toggleMarcarParaEnvio(){
        this.marcarParaEnvio = !this.marcarParaEnvio;
    }
    
    public boolean marcadoParaEnvio(){
        return this.marcarParaEnvio;
    }
    
    @Override
    public String toString() {
        return configDesc;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.fichConfigMaquina);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaquinaConfigFile other = (MaquinaConfigFile) obj;
        if (other.configDesc.equals(other.configDesc) && other.fichConfigMaquina.equals(other.fichConfigMaquina))
            return true;
        return false;
    }
    
    
    
}
