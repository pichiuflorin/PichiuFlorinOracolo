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
            System.out.println("Server: " + serverReply);

            while (true) {
                System.out.print("Inserisci comando: ");
                String command = scr.nextLine();
                
                out.println(command);
                
                serverReply = in.readLine();
                System.out.println("Server: " + serverReply);
                
                if (command.equalsIgnoreCase("quit")) break;
            }

        } catch (IOException e) {
            System.err.println("Impossibile connettersi al server: " + e.getMessage());
        }
    }
    
}
