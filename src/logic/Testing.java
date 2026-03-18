package logic;

public class Testing {
    
    public static void main(String[] args) {
        System.out.println("=== Testing WUNGSdle (Wordle) ===\n");
        
        // Test 1: Palabra correcta
        testCorrectWord();
        
        // Test 2: Letras en posición incorrecta
        testWrongPosition();
        
        // Test 3: Letras no presentes
        testLettersNotPresent();
        
        // Test 4: Mezcla de resultados
        testMixedResults();
        
        // Test 5: Intento ganador
        testWinCondition();
        
        // Test 6: Múltiples intentos
        testMultipleAttempts();
    }
    
    private static void testCorrectWord() {
        System.out.println("Test 1: Palabra completamente correcta");
        Master master = new Master("JAVA");
        int[] result = master.controlarPalabra("JAVA");
        printResult("JAVA", result, new int[]{0, 0, 0, 0});
        System.out.println("✓ Passou\n");
    }
    
    private static void testWrongPosition() {
        System.out.println("Test 2: Letras en posición incorrecta");
        Master master = new Master("JAVA");
        int[] result = master.controlarPalabra("AVAJ");
        printResult("AVAJ", result, new int[]{1, 1, 1, 1});
        System.out.println("✓ Passou\n");
    }
    
    private static void testLettersNotPresent() {
        System.out.println("Test 3: Letras que no están en la palabra");
        Master master = new Master("JAVA");
        int[] result = master.controlarPalabra("BCDE");
        printResult("BCDE", result, new int[]{2, 2, 2, 2});
        System.out.println("✓ Passou\n");
    }
    
    private static void testMixedResults() {
        System.out.println("Test 4: Mezcla de verde, amarillo y gris");
        Master master = new Master("JAVA");
        int[] result = master.controlarPalabra("JXVB");
        // J está correcto (verde), X no existe (gris), V existe pero mal posición (amarillo), B no existe (gris)
        printResult("JXVB", result, new int[]{0, 2, 1, 2});
        System.out.println("✓ Passou\n");
    }
    
    private static void testWinCondition() {
        System.out.println("Test 5: Condición de victoria");
        Master master = new Master("JAVA");
        
        master.controlarPalabra("XXXX");
        master.controlarPalabra("YYYY");
        boolean hasWon = master.Ganaste("JAVA");
        
        System.out.println("¿Ganó con 'JAVA'? " + (hasWon ? "SÍ ✓" : "NO ✗"));
        System.out.println("Intentos realizados: " + master.getIntentos());
        System.out.println("✓ Passou\n");
    }
    
    private static void testMultipleAttempts() {
        System.out.println("Test 6: Múltiples intentos y derrota");
        Master master = new Master("JAVA");
        
        String[] guesses = {"XXXX", "YYYY", "ZZZZ", "BBBB", "CCCC", "DDDD"};
        
        for (String guess : guesses) {
            int[] result = master.controlarPalabra(guess);
            System.out.println("Intento " + master.getIntentos() + ": " + guess);
            printColorCodes(result);
        }
        
        boolean hasLost = master.Perdiste();
        System.out.println("\n¿Perdió después de 6 intentos? " + (hasLost ? "SÍ ✓" : "NO ✗"));
        System.out.println("La palabra secreta era: " + master.getPalabraRandom());
        System.out.println("✓ Passou\n");
    }
    
    private static void printResult(String word, int[] actual, int[] expected) {
        System.out.print("Palabra: " + word + " → Colores: ");
        printColorCodes(actual);
        System.out.print("Esperado: ");
        printColorCodes(expected);
        System.out.print("Match: " + (compareArrays(actual, expected) ? "✓" : "✗") + "\n");
    }
    
    private static void printColorCodes(int[] colors) {
        System.out.print("[");
        for (int i = 0; i < colors.length; i++) {
            String colorName;
            if (colors[i] == 0) {
                colorName = "VERDE";
            } else if (colors[i] == 1) {
                colorName = "AMARILLO";
            } else {
                colorName = "GRIS";
            }
            System.out.print(colorName);
            if (i < colors.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    private static boolean compareArrays(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}