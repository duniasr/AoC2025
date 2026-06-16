package software.ulpgc.aoc.day10.a;

import java.util.List;

public record Machine(List<Integer> diagram, List<List<Integer>> schematics) {
    public int solve() {
        return Solver.calculate(diagram, schematics);
    }
}