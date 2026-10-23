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
        private String currentPersona = "giulia";

        public ClientManagement(Socket client, Oracolo oracolo) {
            this.client = client;
            this.oracolo = oracolo;
        }
        
        @Override
        public void run(){
            try {BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                
                out.println("Ciao, funziono! Scrivi /help per i comandi.");
                
                String command;
                while ((command = in.readLine()) != null){
                    command = command.trim();
                    if (command.isEmpty()){
                        continue;
                    }
                    if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("/quit")){
                        out.println("Hai terminato la connessione.");
                        break;
                    }
                    out.println(generateAnswer(command));
                }
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                try {
                    client.close();
                } catch (IOException e){
                    System.err.println(e);
                }
            }
        }
        
        public String generateAnswer(String command){
            if (!command.startsWith("/")) {
                return "Per fare una domanda usa /ask <domanda>. Scrivi /help per i comandi.";
            }
            
            String[] parts = command.trim().split(" ", 2); //divide "/comando" con quello che c'è dopo e lo mette in un array
            String commands = parts[0].toLowerCase(); //rende il comando tutto piccolo in caso fosse scritto con case diversi
            
            //se l'argument è più lungo di uno, lo divide, altrimenti non c'è
            String argument;
            if (parts.length > 1) {
                argument = parts[1].trim();
            } else {
                argument = "";
            }
            
            switch(commands){
                
                case "/ask":
                    if (argument.isEmpty()) {
                        return "Errore: manca la domanda.";
                    }
                    return oracolo.getAnswer(currentPersona, argument);
                
                case "/persona":
                    if (argument.isEmpty()) {
                        return "Personalita' disponibili: " + oracolo.getPersonas();
                    }
                    if (oracolo.personaExists(argument)){
                        currentPersona = argument.toLowerCase();
                        return "Persona scelta: " + argument + ".";
                    }
                    return "Errore: personalita' inesistente. Disponibili: " + oracolo.getPersonas();
                
                case "/help":
                    return "Comandi: /persona [nome], /ask [domanda], /help, /quit";
                
                default:
                    return "Comando sconosciuto. Scrivi /help.";
        }
    }
}
