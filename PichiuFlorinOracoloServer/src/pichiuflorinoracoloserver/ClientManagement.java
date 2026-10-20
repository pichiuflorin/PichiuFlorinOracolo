package pichiuflorinoracoloserver;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author pichiu.florin
 */
public class ClientManagement extends Thread{
        private Socket client;
        private Oracolo oracolo;

        public ClientManagement(Socket client, Oracolo oracolo) {
            this.client = client;
            this.oracolo = oracolo;
        }
        
        @Override
        public void run(){
            try {BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                
                out.println("Ciao, funziono! (stranamente)");
                
                String command;
                while ((command = in.readLine()) != null){
                    if (command.equalsIgnoreCase("quit")){
                        out.println("Hai rage quittato.");
                        break;
                    }
                    out.println(oracolo.getAnswer(command));
                }
                
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        
        public void generateAnswer(String command){
            String[] parts = command.trim().split(" ", 2); //divide "/comando" con quello che c'è dopo e lo mette in un array
            String commands = parts[0].toLowerCase(); //rende il comando tutto piccolo in caso fosse scritto con case diversi
            
            //se l'argument è più lungo di uno, lo divide, altrimenti non c'è
            String argument;
            if (parts.length > 1) {
                argument = parts[1].trim();
            } else {
                argument = "";
            }
            
            switch(command){
                case "/persona":
                    if (argument.isEmpty()) {
                        return "Personalità disponibili: " + oracolo.getPersonas();
                    }
                }
                
            }
        }
}
