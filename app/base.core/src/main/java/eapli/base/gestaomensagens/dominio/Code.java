package eapli.base.gestaomensagens.dominio;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

/*
    Message type code. A single byte representing an unsigned integer number within the 0-255 range
*/
public class Code implements ValueObject {

    private int codigo;
    
    public Code() {
    }

    public Code(final int codigo) {
        Preconditions.ensure(codigoDentroLimites(codigo), "Codigo só pode assumir valores compreendidos entre 0-255");
        this.codigo = codigo;
    }

    private boolean codigoDentroLimites(long codigo) {
        return !(codigo<0 || codigo>255);
    }

    public int value() {
        return codigo;
    }

    @Override
    public String toString() {
        return String.valueOf(codigo);
    }
    
}
