package software.ulpgc.aoc.day02.b;

import java.util.stream.IntStream;

public class InvalidIdDetector {

    // Comprueba si existe alguna longitud de patrón que reconstruya exactamente el número.
    public static boolean isInvalid(long id) {
        String text = String.valueOf(id);
        return IntStream.rangeClosed(1, text.length() / 2)
                .filter(len -> text.length() % len == 0)
                .anyMatch(len -> isPeriodicPattern(text, len));
    }

    // Extrae el patrón candidato, lo repite matemáticamente y verifica si es idéntico al original.
    private static boolean isPeriodicPattern(String text, int patternLength) {
        return text.equals(text.substring(0, patternLength).repeat(text.length() / patternLength));
    }
}