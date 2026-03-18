package logic;

import java.util.HashMap;
import java.util.Map;

public class LenguageManager {
    private static String currentLanguage = "ES";
    
    private static final Map<String, Map<String, String>> translations = new HashMap<>();
    
    static {
        // Español
        Map<String, String> es = new HashMap<>();
        es.put("title", "Wungsdle");
        es.put("play_button", "Jugar");
        es.put("enter_word", "Ingresa una palabra");
        es.put("check_button", "Verificar");
        es.put("attempt", "Intento: ");
        es.put("write_word", "¡Escribi una palabra!");
        es.put("same_length", "La palabra debe tener la misma longitud");
        es.put("win", "GANASTE La palabra era: ");
        es.put("lose", "¡PERDISTE! La palabra era: ");
        translations.put("ES", es);
        
        // English
        Map<String, String> en = new HashMap<>();
        en.put("title", "Wungsdle");
        en.put("play_button", "Play");
        en.put("enter_word", "Enter a word");
        en.put("check_button", "Check");
        en.put("attempt", "Attempt: ");
        en.put("write_word", "Write a word!");
        en.put("same_length", "The word must have the same length");
        en.put("win", "YOU WIN The word was: ");
        en.put("lose", "YOU LOST! The word was: ");
        translations.put("EN", en);
        
        // Français
        Map<String, String> fr = new HashMap<>();
        fr.put("title", "Wungsdle");
        fr.put("play_button", "Jouer");
        fr.put("enter_word", "Entrez un mot");
        fr.put("check_button", "Vérifier");
        fr.put("attempt", "Tentative: ");
        fr.put("write_word", "Écrivez un mot!");
        fr.put("same_length", "Le mot doit avoir la même longueur");
        fr.put("win", "VOUS AVEZ GAGNÉ Le mot était: ");
        fr.put("lose", "VOUS AVEZ PERDU! Le mot était: ");
        translations.put("FR", fr);
        
        // Italiano
        Map<String, String> it = new HashMap<>();
        it.put("title", "Wungsdle");
        it.put("play_button", "Gioca");
        it.put("enter_word", "Inserisci una parola");
        it.put("check_button", "Verifica");
        it.put("attempt", "Tentativo: ");
        it.put("write_word", "Scrivi una parola!");
        it.put("same_length", "La parola deve avere la stessa lunghezza");
        it.put("win", "HAI VINTO La parola era: ");
        it.put("lose", "HAI PERSO! La parola era: ");
        translations.put("IT", it);
    }
    
    public static void setLanguage(String language) {
        if (translations.containsKey(language)) {
            currentLanguage = language;
        }
    }
    
    public static String get(String key) {
        return translations.get(currentLanguage).getOrDefault(key, key);
    }
    
    public static String getCurrentLanguage() {
        return currentLanguage;
    }
}