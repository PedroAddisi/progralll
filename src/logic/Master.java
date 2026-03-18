package logic;

public class Master {
    //
	private String palabraRandom;//Variable random del juego
    //
	private static  int Intentosmaximos= 6;//intentos  que tiene el jugador para adivinar la palabra
	//
    private int intentos;//contador de intentos realizados
    //
    
    public Master(String secretWord) {
        this.palabraRandom = secretWord.toUpperCase();//pasa a minuscula
        this.intentos = 0;//comienzo de contador
    }
    
    public int[] controlarPalabra(String palabraUsuario) {
        palabraUsuario = palabraUsuario.toUpperCase();//guarda palabra en mayusucla
        
        if (palabraUsuario.length() != palabraRandom.length()) {
            return null; // Palabra con longitud inválida
        }
        
        intentos++;//suma un intento
        //
        int[] result = new int[palabraUsuario.length()];//crea array de numeros para guardar resultado
        //
        boolean[] usada = new boolean[palabraRandom.length()];//array para marcar letras ya usadas en el secreto
        
        //
        for (int i = 0; i < palabraUsuario.length(); i++) {
            if (palabraUsuario.charAt(i) == palabraRandom.charAt(i)) {//si la letra es correcta y está en la posición correcta coloca un 0 en resultado
                result[i] = 0; // el 0 marca Verde
                usada[i] = true;// la marca como usada
            } else {
                result[i] = 2; //el 2 marca  Gris 
            }
        }
        
        //
        for (int i = 0; i < palabraUsuario.length(); i++) {
            if (result[i] != 0) { // Si no es verde
                for (int j = 0; j < palabraRandom.length(); j++) {
                    if (!usada[j] && palabraUsuario.charAt(i) == palabraRandom.charAt(j)) {//si la letra existe en la palabra y no ha sido usada, marca como amarillo
                        result[i] = 1; // 1 marca la letra como Amarillo
                        usada[j] = true;
                        break;
                    }
                }
            }
        }
        
        return result;
    }
    
    public boolean Ganaste(String palabraAdivinada) {
        return palabraAdivinada.toUpperCase().equals(palabraRandom);//si la palabra adivinada es igual a la palabra random, el jugador gana
    }
    
    public boolean Perdiste() {
        return intentos >= Intentosmaximos;//si el jugador ha alcanzado el número máximo de intentos sin adivinar la palabra, pierde
    }
    
    public int getIntentos() {
        return intentos;
    }
    
    public String getPalabraRandom() {
        return palabraRandom;
    }
}