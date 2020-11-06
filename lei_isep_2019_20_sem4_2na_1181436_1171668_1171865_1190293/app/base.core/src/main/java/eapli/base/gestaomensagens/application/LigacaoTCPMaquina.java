package eapli.base.gestaomensagens.application;

import eapli.base.gestaomaquinas.domain.NumeroIdentificacaoUnico;
import eapli.base.gestaomensagens.dominio.Codes;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.dominio.Versoes;
import eapli.base.gestaomensagens.protocolo.CodificarService;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class LigacaoTCPMaquina {

    private Socket cliSock;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    private InetAddress clientIP;
    private CodificarService codSvc = new CodificarService();

    public void iniciarLigacao(Socket cliSock) throws Exception {
        this.cliSock = cliSock;
        ListaClientesMaquina.addCli(this.cliSock);
        setIPCliente();
        setOutputEntrada();
        setOutputSaida();
    }

    public void desligarLigacao() throws Exception {
        ListaClientesMaquina.remCli(cliSock);
    }

    private void setIPCliente() {
        this.clientIP = cliSock.getInetAddress();
    }
    
    public InetAddress clientIP(){
        return this.clientIP;
    }
    
    public int porta(){
        return this.cliSock.getPort();
    }

    private void setOutputSaida() throws IOException {
        this.sOut = new DataOutputStream(cliSock.getOutputStream());
    }

    private void setOutputEntrada() throws IOException {
        this.sIn = new DataInputStream((cliSock.getInputStream()));
    }

    public Mensagem receberMensagem() throws IOException {
        byte version;
        byte code;
        short id;
        short dataLength;
        String rawData = "";

        version = receberVersion();
        if (version == -1) {
            return null;
        }
        code = receberCode();
        id = receberId();
        dataLength = receberDataLength();
        if (dataLength > 0) {
            rawData = receberRawData(dataLength);
        }

        return new Mensagem(version, code, id, dataLength, rawData);
    }

    private byte receberVersion() throws IOException {
        return (byte) sIn.read();
    }

    private byte receberCode() throws IOException {
        return (byte) sIn.read();
    }

    private short receberId() throws IOException {
        short id = 0;
        int i, f = 1;
        for (i = 0; i < 2; i++) {
            id = (short) (id + f * sIn.read());
            f = f * 256;
        }
        return id;
    }

    private short receberDataLength() throws IOException {
        short dataLength = 0;
        int i, f = 1;
        for (i = 0; i < 2; i++) {
            dataLength = (short) (dataLength + f * sIn.read());
            f = f * 256;
        }
        return dataLength;
    }

    private String receberRawData(short dataLength) throws IOException {
        byte[] rawDataBytes = new byte[dataLength];
        int i;

        for (i = 0; i < dataLength; i++) {
            rawDataBytes[i] = sIn.readByte();
        }
        return new String(rawDataBytes);
    }

    public void enviarAck() throws IOException {
        String ack = "OK";
        Mensagem msg = new Mensagem(Versoes.VERS_0.getVersao(), Codes.ACK.getCode(), 0, ack.length(), ack);
        byte[] msgCod = codSvc.codificar(msg);
        int i;
        for (i = 0; i < msgCod.length; i++) {
            sOut.write(msgCod[i]);
        }
    }

    public void enviarNack(String erro) throws IOException {
        Mensagem msg = new Mensagem(Versoes.VERS_0.getVersao(), Codes.NACK.getCode(), 1, erro.length(), erro);
        byte[] msgCod = codSvc.codificar(msg);
        int i;
        for (i = 0; i < msgCod.length; i++) {
            sOut.write(msgCod[i]);
        }
    }
    
    public void enviarConfig(byte[] config, NumeroIdentificacaoUnico nui) throws IOException{
        Mensagem msg = new Mensagem(Versoes.VERS_0.getVersao(), Codes.CONFIG.getCode(), 
                Integer.parseInt(nui.toString()), config.length, config);
        byte[] msgCod = codSvc.codificar(msg);
        int i;
        for (i = 0; i < msgCod.length; i++) {
            sOut.write(msgCod[i]);
        }
    }
}
