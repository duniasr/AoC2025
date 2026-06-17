package software.ulpgc.aoc.day10.a;

import java.util.List;

public record FactoryMachine(List<Integer> indicatorLights, List<List<Integer>> wiringSchematics) {

    public int minimumPresses() {
        return LightConfigurator.minimumPressesFor(indicatorLights, wiringSchematics);
    }
}