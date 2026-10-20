package pichiuflorinoracoloclient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author pichiu.florin
 */
public class PichiuFlorinOracoloClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int port = 5555;
            String host = "LocalHost";
            Socket socket = new Socket(host, port);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scr = new Scanner(System.in);
            
            String serverReply = in.readLine();
            System.out.println("Oracolo: " + serverReply);

            while (true) {
                System.out.print("Inserisci comando: ");
                String comando = scr.nextLine();
                
                out.println(comando);
                
                serverReply = in.readLine();
                System.out.println("Oracolo: " + serverReply);
            }

        } catch (IOException e) {
            System.err.println("Impossibile connettersi al server: " + e.getMessage());
        }
    }
    
}
