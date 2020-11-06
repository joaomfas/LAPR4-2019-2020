package eapli.base.gestaomensagens.application;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class SocketServerTCP {

    private static final String TRUSTED_STORE = System.getProperty("user.home")+"/certs/server_J.jks";
    private static final String KEYSTORE_PASS = "forgotten";

    private SSLServerSocket sslSock = null;

    //private ServerSocket sock = null;

    public SocketServerTCP(int porta) throws IOException {
        //sock = new ServerSocket(porta);

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            sslSock = (SSLServerSocket) sslF.createServerSocket(porta);
            sslSock.setNeedClientAuth(true);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Falha ao abrir servidor na porta " + porta);
        }
    }

    public Socket aceitarLigacao() throws IOException {
        return sslSock.accept();
    }
}
