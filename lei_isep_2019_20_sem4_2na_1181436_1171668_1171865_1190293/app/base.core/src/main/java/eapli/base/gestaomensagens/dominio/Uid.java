package eapli.base.gestaomensagens.dominio;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

/*
    Industrial machine’s unique identification number.
    Number = (first byte + 256 x second byte)If zero, 
    means not applicable to the present message.
*/
public class Uid implements ValueObject {
    
    int uid;

    public Uid() {
        uid=0;
    }
    
    public Uid(int uid) {
        Preconditions.ensure(UidDentroLimites(uid), "Id só pode assumir valores compreendidos entre 0-65535");
        this.uid = uid;
    }

    //Uid é 0 no primeiro envio Hello (SMM)
    private boolean UidDentroLimites(int uid) {
        return !(uid<0 || uid>65535);
    }

    public int value() {
        return uid;
    }
    
    @Override
    public String toString() {
        return String.valueOf(uid);
    }
    
}
