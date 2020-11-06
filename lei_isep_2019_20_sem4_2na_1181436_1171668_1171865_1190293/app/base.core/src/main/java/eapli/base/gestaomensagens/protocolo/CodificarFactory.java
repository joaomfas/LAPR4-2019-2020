package eapli.base.gestaomensagens.protocolo;

public class CodificarFactory {
    public final ICodificar build(int versao){
        
        switch (versao) {
        case 0:
            return new CodificarV0();
        }
        
        throw new IllegalStateException("Versão inválida!");
    }
}
