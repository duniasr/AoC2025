package software.ulpgc.aoc.day07.a;

import software.ulpgc.aoc.day07.common.ManifoldPhysics;
import software.ulpgc.aoc.day07.common.SimulatorEngine;

import java.util.*;
import java.util.stream.*;

public class TachyonManifold {
    public record State(Set<Integer> beams, long splits) {}

    public static long calculate(List<String> grid) {
        return SimulatorEngine.run(grid, new State(Set.of(ManifoldPhysics.findStart(grid)), 0L),
                TachyonManifold::process).splits();
    }

    private static State process(State s, String row) {
        return new State(evolveAll(s.beams(), row), s.splits() + countSplits(s.beams(), row));
    }

    private static Set<Integer> evolveAll(Set<Integer> beams, String row) {
        return beams.stream().flatMap(b -> ManifoldPhysics.isSplitter(row, b) ? Stream.of(b-1, b+1) : Stream.of(b))
                .filter(b -> ManifoldPhysics.isWithinBounds(row, b)).collect(Collectors.toSet());
    }

    private static long countSplits(Set<Integer> beams, String row) {
        return beams.stream().filter(b -> ManifoldPhysics.isSplitter(row, b)).count();
    }
}