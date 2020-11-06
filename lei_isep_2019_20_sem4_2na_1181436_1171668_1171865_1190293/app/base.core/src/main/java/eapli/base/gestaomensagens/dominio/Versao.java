package eapli.base.gestaomensagens.dominio;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class Versao implements ValueObject {
    
    private int versao;
    
    public Versao() {
        versao = 0;
    }

    public Versao(final int versao) {
        Preconditions.ensure(versaoDentroLimites(versao), "Versão só pode assumir valores compreendidos entre 0-255");
        this.versao = versao;
    }


    private boolean versaoDentroLimites(int versao) {
        return !(versao<0 || versao>255);
    }

    public int value() {
        return versao;
    }

    @Override
    public String toString() {
        return String.valueOf(versao);
    }
    
    

}
