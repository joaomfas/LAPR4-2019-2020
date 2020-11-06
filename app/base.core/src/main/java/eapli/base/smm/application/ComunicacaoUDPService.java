package eapli.base.smm.application;

import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.protocolo.CodificarService;
import eapli.base.gestaomensagens.protocolo.DescodificarService;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class ComunicacaoUDPService {

    private static final int MAX_SIZE = 300;

    private static final int DEFAULT_TIMEOUT = 10000;//milliseconds

    private final InetAddress ipAddress;
    private final int porta;
    private final int timeout;

    private final DescodificarService descodificarService = new DescodificarService();
    private final CodificarService codificarService = new CodificarService();
    
    public ComunicacaoUDPService(InetAddress ipAddress, int porta) {
        this.ipAddress = ipAddress;
        this.porta = porta;
        this.timeout = DEFAULT_TIMEOUT;
    }
    
    public ComunicacaoUDPService(InetAddress ipAddress, int porta, int timeout) {
        this.ipAddress = ipAddress;
        this.porta = porta;
        this.timeout = timeout;
    }
    
    public Mensagem enviaMensagem(Mensagem msg) throws SocketException, IOException {
        DatagramSocket socket = openSocket(false);
        DatagramPacket udpPacket = envia(socket, msg);
        Mensagem resposta = recebe(socket, udpPacket);
        closeSocket(socket);
        return resposta;
    }
    
    public Map<InetAddress, Mensagem> enviaMensagem(Mensagem msg, int respostasEsperadas) {
        Map<InetAddress, Mensagem> respostas = new HashMap();
        try {
            DatagramSocket socket = openSocket(true);
            DatagramPacket udpPacket = envia(socket, msg);   
            while(respostas.size()<respostasEsperadas) {
                Mensagem mensagemRecebida = recebe(socket, udpPacket);
                respostas.put(udpPacket.getAddress(), mensagemRecebida);
            }
            closeSocket(socket);
        } catch (IOException ex) {
        }
        return respostas;
    }
    
    private DatagramSocket openSocket(boolean isBroadCast) throws SocketException {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(timeout);
        socket.setBroadcast(isBroadCast);
        return socket;
    }

    private void closeSocket(DatagramSocket socket) {
        socket.close();
    }

    private DatagramPacket envia(DatagramSocket socket, Mensagem msg) throws IOException {
        byte[] data = new byte[MAX_SIZE];
        DatagramPacket udpPacket = new DatagramPacket(data, data.length, ipAddress, porta);
        byte[] mensagem = codificarService.codificar(msg);
        udpPacket.setData(mensagem);
        udpPacket.setLength(mensagem.length);
        socket.send(udpPacket);
        return udpPacket;
    }

    private Mensagem recebe(DatagramSocket socket, DatagramPacket udpPacket) throws IOException {
        byte[] data = new byte[MAX_SIZE];
        udpPacket.setData(data);
        udpPacket.setLength(MAX_SIZE);
        socket.receive(udpPacket);
        Mensagem m = descodificarService.descodificar(udpPacket.getData());
        return m;
    }
    
    
    
}