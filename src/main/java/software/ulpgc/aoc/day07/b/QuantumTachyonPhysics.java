package software.ulpgc.aoc.day07.b;

import software.ulpgc.aoc.day07.TachyonPhysics;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuantumTachyonPhysics implements TachyonPhysics<QuantumTachyonPhysics.State> {

    public record State(Map<Integer, Long> timelines) {
        public long totalActiveTimelines() {
            return timelines.values().stream().mapToLong(Long::longValue).sum();
        }
    }

    @Override
    public State initialize(String firstRow) {
        return new State(Map.of(firstRow.indexOf('S'), 1L));
    }

    @Override
    public State propagateThrough(State state, String row) {
        Map<Integer, Long> nextTimelines = state.timelines().entrySet().stream()
                .flatMap(entry -> applyQuantumSplit(entry, row))
                .filter(entry -> isWithinManifold(row, entry.getKey()))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)));

        return new State(nextTimelines);
    }

    private Stream<Map.Entry<Integer, Long>> applyQuantumSplit(Map.Entry<Integer, Long> entry, String row) {
        int pos = entry.getKey();
        long timelinesCount = entry.getValue();

        return hitSplitter(row, pos)
                ? Stream.of(Map.entry(pos - 1, timelinesCount), Map.entry(pos + 1, timelinesCount))
                : Stream.of(Map.entry(pos, timelinesCount));
    }

    private boolean hitSplitter(String row, int pos) {
        return row.charAt(pos) == '^';
    }

    private boolean isWithinManifold(String row, int pos) {
        return pos >= 0 && pos < row.length();
    }
}