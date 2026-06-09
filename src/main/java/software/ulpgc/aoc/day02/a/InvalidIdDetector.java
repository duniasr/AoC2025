package main.java.software.ulpgc.aoc.day02.a;

public class InvalidIdDetector {

    // Comprueba si la representación en texto del número se divide en dos mitades idénticas.
    public static boolean isInvalid(long id) {
        return checkSymmetry(String.valueOf(id));
    }

    // Un texto es simétrico si su longitud es par y su primera mitad es igual a la segunda.
    private static boolean checkSymmetry(String text) {
        return text.length() % 2 == 0 && text.substring(0, text.length() / 2).equals(text.substring(text.length() / 2));
    }
}