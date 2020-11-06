package eapli.base.gestaomensagens.protocolo;

public class DescodificarFactory {
    
    public final IDescodificar build(int versao) {
        switch (versao) {
        case 0:
            return new DescodificarV0();
        }
        throw new IllegalStateException("Versão inválida!");
    }
    
}
