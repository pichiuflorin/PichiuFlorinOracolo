package pichiuflorinoracoloserver;

/**
 *
 * @author pichiu.florin
 */
public class Oracolo {
    private final Map<String, Persona> personalita = new HashMap<>();
    
    public Oracolo() {
        personalita.put("Giulia", new Persona("Giulia", Map.of(
            "ciao", "Ciaoo",
            "come stai", "Bene, grazie."
        )));
    }
    
    public synchronized String getAnswer(String command){
        if (command.equalsIgnoreCase("Ciao")){
            return("Hai scritto: " + command);
        } else {
            return ("Ok");
        }
    }
    
    public String getPersonas(){
        return("Giulia, ...");
    }
}
