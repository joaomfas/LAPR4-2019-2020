package eapli.base.gestaomensagens.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class SCMServerThreadLinha implements Runnable, ICarregamentoMensagemImporter {

    private static HashMap<Socket, DataOutputStream> cliList = new HashMap<>();
    private LinhaProducao linhaProducao;

    public static synchronized void sendToAll(int len, byte[] data) throws Exception {
        for (DataOutputStream cOut : cliList.values()) {
            cOut.write(len);
            cOut.write(data, 0, len);
        }
    }

    public static synchronized void addCli(Socket s) throws Exception {
        cliList.put(s, new DataOutputStream(s.getOutputStream()));
    }

    public static synchronized void remCli(Socket s) throws Exception {
        cliList.get(s).write(0);
        cliList.remove(s);
        s.close();
    }

    @Override
    public void receiveParameter(Object param) {
        this.linhaProducao = (LinhaProducao) param;
    }

    @Override
    public void run() {
        SocketServerTCP sock = null;
        
        try {
            sock = new SocketServerTCP(9000 + Integer.parseInt(linhaProducao.identity()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println("Servidor SCM iniciado para a linha de produção: " + linhaProducao.identity());
        while (true) {
            try {
                LigacaoTCPMaquina ligacao = new LigacaoTCPMaquina();
                ligacao.iniciarLigacao(sock.aceitarLigacao());
                SCMServerThreadMaquina thread = new SCMServerThreadMaquina(ligacao);
                thread.setLinhaProducao(linhaProducao);
                new Thread(thread).start();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
