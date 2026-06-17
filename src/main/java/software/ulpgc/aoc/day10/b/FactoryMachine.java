package software.ulpgc.aoc.day10.b;

import java.util.List;

public record FactoryMachine(List<Integer> joltageTargets, List<List<Integer>> wiringSchematics) {
    public long minimumJoltagePresses() {
        return JoltageConfigurator.minimumPressesToAchieve(joltageTargets, wiringSchematics);
    }
}