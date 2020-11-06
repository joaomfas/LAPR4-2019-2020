package eapli.base.gestaomensagens.protocolo;

import eapli.base.gestaomensagens.dominio.*;

public class DescodificarV0 implements IDescodificar {
    
    
    
    @Override
    public Mensagem descodificarMensagem(byte[] data) {
        
        int version = 0xFF & data[0];
        
        int code = 0xFF & data[1];
        
        int id = data[2] + 256 * data[3];
        
        int dataLength = data[4] + 256 * data[5];
             
        byte[] rawData = new byte[dataLength];
        for(int i=0; i<dataLength; i++) {
            rawData[i] = data[i+6];
        }
        
        return new Mensagem(version, code, id, dataLength, new String(rawData) );
        
    }
    
}
