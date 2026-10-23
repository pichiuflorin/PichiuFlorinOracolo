package pichiuflorinoracoloserver;

import java.util.*;

/**
 *
 * @author pichiu.florin
 */
public class Oracolo {
    private final Map<String, Map<String, String>> persona = new HashMap<>();
    
    public Oracolo() {
        Map<String, String> giulia = new HashMap<>();
        giulia.put("ciao", "Ciaoo!");
        giulia.put("come stai", "Bene, grazie.");
        giulia.put("come ti chiami", "Mi chiamo Giulia.");
        persona.put("giulia", giulia);
 
        Map<String, String> aisa = new HashMap<>();
        aisa.put("ciao", "Buongiorno.");
        aisa.put("come stai", "Starei meglio se mi dessi il quadernone. Devo finire di mettere le valutazioni a italiano.");
        aisa.put("come ti chiami", "Mi chiamo Aisa Emanuela.");
        persona.put("aisa", aisa);
        
        Map<String, String> poggioni = new HashMap<>();
        poggioni.put("ciao", "BUONGIORNOOO!");
        poggioni.put("come stai", "Bene, ma sono appena tornata dal 5D e sono stanca.");
        poggioni.put("come ti chiami", "Mi chiamo Poggioni Marta.");
        persona.put("poggioni", poggioni);
    }
    
    public String getAnswer(String personaName, String message){
        Map<String, String> risposte = persona.get(personaName.toLowerCase());
        if (risposte == null){
            return "Errore: Personalita' inesistente.";
        }
        String m = message.trim().toLowerCase();
        for (Map.Entry<String, String> e : risposte.entrySet()){
            if (m.contains(e.getKey())){
                return e.getValue();
            } 
        }
        return "Non saprei cosa risponderti...";
    }
    
    public boolean personaExists(String name){
        return persona.containsKey(name.toLowerCase());
    }
    
    public String getPersonas(){
        String elenco = "";
        for (String nome: persona.keySet()){
            if (!elenco.isEmpty()){
                elenco += ", ";
            }
            elenco += Character.toUpperCase(nome.charAt(0)) + nome.substring(1);
        }
        return elenco;
    }
}
