package software.ulpgc.aoc.day05.a;

import java.util.Arrays;
import java.util.List;

public class InventoryAnalyzer {

    public static long countFreshIngredients(String fullInput) {
        String[] sections = fullInput.split("\n\\s*\n");
        List<IngredientRange> ranges = parseRanges(sections[0]);

        return Arrays.stream(sections[1].split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong)
                .filter(id -> ranges.stream().anyMatch(r -> r.contains(id)))
                .count();
    }
    private static List<IngredientRange> parseRanges(String rangesText) {
        return Arrays.stream(rangesText.split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(IngredientRange::parse)
                .toList();
    }
}