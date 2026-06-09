package main.java.software.ulpgc.aoc.day03.common;

import java.util.Arrays;
import java.util.function.ToLongFunction;

// Orquestador genérico: procesa el input y aplica la estrategia de cálculo inyectada.
public class PowerGrid {

    // Recibe el texto completo y la función "Inspector" que sabe calcular el máximo de una línea.
    public static long calculateTotalJoltage(String fullInput, ToLongFunction<String> bankProcessor) {
        return Arrays.stream(fullInput.split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToLong(bankProcessor)
                .sum();
    }
}