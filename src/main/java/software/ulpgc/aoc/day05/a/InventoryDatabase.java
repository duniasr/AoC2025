package software.ulpgc.aoc.day05.a;

import java.util.Arrays;
import java.util.List;

public class InventoryDatabase {

    private final String rawDatabase;

    private InventoryDatabase(String rawDatabase) {
        this.rawDatabase = rawDatabase;
    }

    public static InventoryDatabase from(String rawDatabase) {
        return new InventoryDatabase(rawDatabase);
    }

    public long countFreshAvailableIngredients() {
        String[] sections = rawDatabase.split("\\R\\s*\\R");

        List<FreshIdRange> freshRanges = parseRanges(sections[0]);

        return Arrays.stream(sections[1].split("\\R"))
                .map(String::trim).filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong)
                .filter(id -> freshRanges.stream().anyMatch(range -> range.covers(id)))
                .count();
    }

    private static List<FreshIdRange> parseRanges(String rangesSection) {
        return Arrays.stream(rangesSection.split("\\R"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(FreshIdRange::from)
                .toList();
    }
}