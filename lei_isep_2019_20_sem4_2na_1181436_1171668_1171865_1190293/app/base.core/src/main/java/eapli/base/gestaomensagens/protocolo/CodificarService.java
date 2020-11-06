package eapli.base.gestaomensagens.protocolo;

import eapli.base.gestaomensagens.dominio.Mensagem;

public class CodificarService {
    public byte[] codificar(Mensagem m) {
        
        ICodificar codificar = new CodificarFactory().build(m.versao());
        
        return codificar.codificarMensagem(m);
        
    }
}
