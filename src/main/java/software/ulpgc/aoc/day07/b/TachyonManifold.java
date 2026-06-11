package software.ulpgc.aoc.day07.b;

import software.ulpgc.aoc.day07.common.ManifoldPhysics;
import software.ulpgc.aoc.day07.common.SimulatorEngine;

import java.util.*;
import java.util.stream.*;

public class TachyonManifold {
    public record State(Map<Integer, Long> timelines) {
        public long count() { return timelines.values().stream().mapToLong(Long::longValue).sum(); }
    }

    public static long calculate(List<String> grid) {
        return SimulatorEngine.run(grid, new State(Map.of(ManifoldPhysics.findStart(grid), 1L)),
                TachyonManifold::process).count();
    }

    private static State process(State s, String row) {
        return new State(s.timelines().entrySet().stream().flatMap(e -> evolve(row, e.getKey(), e.getValue()))
                .filter(e -> ManifoldPhysics.isWithinBounds(row, e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum)));
    }

    private static Stream<Map.Entry<Integer, Long>> evolve(String row, int pos, long count) {
        return ManifoldPhysics.isSplitter(row, pos) ? Stream.of(Map.entry(pos-1, count), Map.entry(pos+1, count))
                : Stream.of(Map.entry(pos, count));
    }
}