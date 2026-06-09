package main.java.software.ulpgc.aoc.day03.a;

import java.util.stream.IntStream;

public class BatteryBank {

    // Extrae el mayor voltaje posible combinando exactamente dos dígitos.
    public static long maxJoltage(String bank) {
        return IntStream.range(0, bank.length() - 1)
                .mapToLong(i -> maxStartingAt(bank, i))
                .max()
                .orElse(0);
    }

    // Fija un primer dígito y busca el más grande que haya a su derecha.
    private static long maxStartingAt(String bank, int firstDigitIndex) {
        int firstDigit = Character.getNumericValue(bank.charAt(firstDigitIndex));
        int maxSecondDigit = findMaxDigit(bank.substring(firstDigitIndex + 1));
        return (firstDigit * 10L) + maxSecondDigit;
    }

    // Encuentra el dígito más alto en una subcadena de texto.
    private static int findMaxDigit(String text) {
        return text.chars().map(Character::getNumericValue).max().orElse(0);
    }
}