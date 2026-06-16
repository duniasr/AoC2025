package software.ulpgc.aoc.day10.b;

import java.util.List;

public record Machine(List<Integer> joltageTarget, List<List<Integer>> schematics) {
    public long solve() {
        return Solver.calculate(joltageTarget, schematics);
    }
}