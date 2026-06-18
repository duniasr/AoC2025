package software.ulpgc.aoc.day07.a;

import software.ulpgc.aoc.day07.common.TachyonPhysics;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassicalTachyonPhysics implements TachyonPhysics<ClassicalTachyonPhysics.State> {

    public record State(Set<Integer> activeBeams, long totalSplits) {}

    @Override
    public State initialize(String firstRow) {
        return new State(Set.of(firstRow.indexOf('S')), 0L);
    }

    @Override
    public State propagateThrough(State state, String row) {
        Set<Integer> nextBeams = calculateNextPositions(state.activeBeams(), row);
        long newSplits = countNewSplits(state.activeBeams(), row);

        return new State(nextBeams, state.totalSplits() + newSplits);
    }

    private Set<Integer> calculateNextPositions(Set<Integer> beams, String row) {
        return beams.stream()
                .flatMap(pos -> hitSplitter(row, pos) ? Stream.of(pos - 1, pos + 1) : Stream.of(pos))
                .filter(pos -> isWithinManifold(row, pos))
                .collect(Collectors.toSet());
    }

    private long countNewSplits(Set<Integer> beams, String row) {
        return beams.stream()
                .filter(pos -> hitSplitter(row, pos))
                .count();
    }

    private boolean hitSplitter(String row, int pos) {
        return row.charAt(pos) == '^';
    }

    private boolean isWithinManifold(String row, int pos) {
        return pos >= 0 && pos < row.length();
    }
}