package pichiuflorinoracoloserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author pichiu.florin
 */
public class PichiuFlorinOracoloServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket Socket;
        Socket client;
        Oracolo oracolo = new Oracolo();
        int port = 5555; 
        
        try {
            Socket = new ServerSocket(port);
            
            System.out.println("In attesa di connessioni...");
            while (true) {
                client = Socket.accept();
                System.out.println("Connessione stabilita con " + client.getInetAddress().toString());
                ClientManagement clientManagement = new ClientManagement(client, oracolo);
                clientManagement.start();      
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
}
