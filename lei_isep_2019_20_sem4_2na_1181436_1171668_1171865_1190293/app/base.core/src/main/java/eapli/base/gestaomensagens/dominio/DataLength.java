package eapli.base.gestaomensagens.dominio;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

/*
    Number of bytes stored in thefollowing RAW DATA 
    field.Number = (first byte + 256 x second byte)
    The total length of the message is (6 + DATA LENGTH) bytes.
    DATA LENGTH may be zero, meaning there’s no RAW DATA, 
    and thus the total message length is 6 bytes.
*/

class DataLength implements ValueObject {
    
    private int length;

    public DataLength() {
    }

    public DataLength(int length) {
        Preconditions.ensure(verificaTamanho(length), "O tamanho pode assumir valores compreendidos entre 0-65535");
        this.length = length;
    }

    private boolean verificaTamanho(int length) {
        return !(length<0 || length>65535);
    }

    @Override
    public String toString() {
        return String.valueOf(length);
    }

    public int value() {
        return length;
    }
    
}
