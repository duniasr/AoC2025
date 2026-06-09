package software.ulpgc.aoc.day03.b;

import java.util.stream.IntStream;

public class BatteryBank {

    // Inicia la recursión pidiendo exactamente 12 dígitos y lo convierte a número.
    public static long maxJoltage(String bank) {
        return Long.parseLong(maxSubsequence(bank, 12));
    }

    // Busca el mejor índice dentro del "escaparate" disponible y recursa para el resto.
    private static String maxSubsequence(String text, int keep) {
        return keep == 0 ? "" : appendMaxAndRecurse(text, keep, findMaxIndex(text.substring(0, text.length() - keep + 1)));
    }

    // Concatena el número ganador y vuelve a llamar a la función con el texto sobrante.
    private static String appendMaxAndRecurse(String text, int keep, int maxIdx) {
        return text.charAt(maxIdx) + maxSubsequence(text.substring(maxIdx + 1), keep - 1);
    }

    // Encuentra la posición del número más grande en el texto.
    private static int findMaxIndex(String window) {
        return IntStream.range(0, window.length())
                .reduce((a, b) -> window.charAt(a) < window.charAt(b) ? b : a).orElse(0);
    }
}