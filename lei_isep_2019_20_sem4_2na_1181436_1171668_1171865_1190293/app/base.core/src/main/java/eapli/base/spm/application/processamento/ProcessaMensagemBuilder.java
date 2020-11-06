package eapli.base.spm.application.processamento;

import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;

public class ProcessaMensagemBuilder {

    public static ProcessaMensagem build(Mensagem mensagem) throws SPMException {
        
        String rawData = mensagem.rawData().toString();
        String[] campos = rawData.split(";");
        if(campos.length<2) {
            return null;
        }
        Tipo tipo = Tipo.valueOf(campos[1]);
        
        switch(tipo) {
            case C0:
                return new C0(mensagem);
            case C9:
                return new C9(mensagem);
            case P1:
                return new P1(mensagem);
            case P2:
                return new P2(mensagem);
            case S0:
                return new S0(mensagem);
            case S1:
                return new S1(mensagem);
            case S8:
                return new S8(mensagem);
            case S9:
                return new S9(mensagem);
        }
        return null;
        
    }
    
}
