package eapli.base.gestaomensagens.protocolo;

import eapli.base.gestaomensagens.dominio.*;

public class CodificarV0 implements ICodificar {

    @Override
    public byte[] codificarMensagem(Mensagem m) {//Versoes ver, Codes code, int id, int dataLength, String rawData) {

        int bt = 0, num = 0, i;
        byte[] bytes = new byte[6 + m.dataLength()];
        byte[] data = m.rawData().toBytes();

        bytes[0] = (byte) m.versao();
        bytes[1] = (byte) m.code();
        num = m.id();
        for (i = 0; i < 2; i++) {
            bt = num % 256;
            bytes[2 + i] = (byte) bt;
            num = num / 256;
        }
        num = m.dataLength();
        for (i = 0; i < 2; i++) {
            bt = num % 256;
            bytes[4 + i] = (byte) bt;
            num = num / 256;
        }

        for (i = 0; i < data.length; i++) {
            bytes[6 + i] = data[i];
        }

        return bytes;
    }

}
