package pichiuflorinoracoloclient;

import java.io.*;
import java.net.*;

/**
 *
 * @author pichiu.florin
 */
public class ClientConnection {
    public String host = "localhost";
    public int port = 5555;
 
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean connected = false;
 
    public boolean isConnected() {
        return connected;
    }
    
    public void connect() throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        connected = true;
    }
    
    public boolean sendCommand(String command) {
        if (!connected) {
            return false;
        }
        out.println(command);
        return true;
    }
    
    public String ask(String question) {
        String command = "/ask " + question;
        return sendCommand(command) ? command : null;
    }
 
    public String persona(String name) {
        String command = "/persona " + name;
        return sendCommand(command) ? command : null;
    }
 
    public String help() {
        String command = "/help";
        return sendCommand(command) ? command : null;
    }
 
    public String quit() {
        String command = "/quit";
        return sendCommand(command) ? command : null;
    }
    
    public void close() {
        connected = false;
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    //aspetta una riga di risposta dal server
    //ritorna null se la connessione è chiusa (e in quel caso chiude tutto)
    public String readReply() {
        try {
            String line = in.readLine();
            if (line == null) {
                close();
            }
            return line;
        } catch (IOException e) {
            close();
            return null;
        }
    }
}
