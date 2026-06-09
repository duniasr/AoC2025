package software.ulpgc.aoc.day05.b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryAnalyzer {

    public static long countTotalFreshCapacity(String fullInput) {
        String[] sections = fullInput.split("\n\\s*\n");
        List<IngredientRange> ranges = parseRanges(sections[0]);
        // 1. Ordenamos los rangos de menor a mayor
        List<IngredientRange> sortedRanges = ranges.stream().sorted().toList();
        // 2. Fusionamos los solapamientos
        List<IngredientRange> mergedRanges = mergeOverlappingRanges(sortedRanges);
        // 3. Sumamos los tamaños de los rangos resultantes
        return mergedRanges.stream()
                .mapToLong(IngredientRange::size)
                .sum();
    }

    private static List<IngredientRange> parseRanges(String rangesText) {
        return Arrays.stream(rangesText.split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(IngredientRange::parse)
                .toList();
    }

    // Algoritmo clásico de fusión de intervalos
    private static List<IngredientRange> mergeOverlappingRanges(List<IngredientRange> sortedRanges) {
        if (sortedRanges.isEmpty()) return new ArrayList<>();

        List<IngredientRange> merged = new ArrayList<>();
        IngredientRange current = sortedRanges.get(0);

        for (int i = 1; i < sortedRanges.size(); i++) {
            IngredientRange next = sortedRanges.get(i);

            if (current.canMergeWith(next)) {
                current = current.mergeWith(next); // Se derriten en uno solo
            } else {
                merged.add(current); // Se guardan y pasamos al siguiente bloque
                current = next;
            }
        }
        merged.add(current); // Guarda el último rezagado

        return merged;
    }
}