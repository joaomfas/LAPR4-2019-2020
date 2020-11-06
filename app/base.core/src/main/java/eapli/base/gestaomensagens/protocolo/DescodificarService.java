package eapli.base.gestaomensagens.protocolo;

import eapli.base.gestaomensagens.dominio.Mensagem;

public class DescodificarService {

    public Mensagem descodificar(byte[] data) {
        
        if(data.length==0)
            return null;
        
        int ver = 0xFF & data[0];
        
        IDescodificar descodificar = new DescodificarFactory().build(ver);
        
        return descodificar.descodificarMensagem(data);
        
    }
    
}
