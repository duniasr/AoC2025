package software.ulpgc.aoc.day05.b;

import java.util.ArrayList;
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

    public long calculateTotalFreshCapacity() {
        String[] sections = rawDatabase.split("\\R\\s*\\R");
        List<FreshIdRange> freshRanges = parseRanges(sections[0]);
        List<FreshIdRange> sortedRanges = freshRanges.stream().sorted().toList();

        List<FreshIdRange> mergedRanges = sortedRanges.stream().collect(
                ArrayList::new,
                InventoryDatabase::accumulateRange,
                ArrayList::addAll
        );

        return mergedRanges.stream()
                .mapToLong(FreshIdRange::size)
                .sum();
    }

    private static List<FreshIdRange> parseRanges(String rangesSection) {
        return Arrays.stream(rangesSection.split("\\R"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(FreshIdRange::from)
                .toList();
    }

    private static void accumulateRange(List<FreshIdRange> merged, FreshIdRange next) {
        if (!merged.isEmpty() && merged.getLast().canMergeWith(next)) {
            merged.set(merged.size() - 1, merged.getLast().mergeWith(next));
        } else {
            merged.add(next);
        }
    }
}