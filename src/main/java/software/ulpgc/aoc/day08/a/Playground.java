package software.ulpgc.aoc.day08.a;

import software.ulpgc.aoc.day08.ElectricalGrid;
import software.ulpgc.aoc.day08.JunctionBox;
import software.ulpgc.aoc.day08.StringOfLights;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Playground {
    private final List<JunctionBox> boxes;
    private final ElectricalGrid grid;

    public Playground(List<JunctionBox> boxes, ElectricalGrid grid) {
        this.boxes = boxes;
        this.grid = grid;
    }

    private Stream<StringOfLights> allStrings() {
        return IntStream.range(0, boxes.size()).boxed().flatMap(i -> IntStream.range(i + 1, boxes.size())
                .mapToObj(j -> StringOfLights.between(boxes.get(i), boxes.get(j))));
    }

    public Playground connectShortestStrings(int maxCables) {
        allStrings().sorted(Comparator.comparingDouble(StringOfLights::length)).limit(maxCables)
                .forEach(lights -> grid.tryConnecting(lights.boxA(), lights.boxB()));
        return this;
    }

    public long productOfLargestCircuits(int count) {
        return grid.getCircuitSizes().stream().sorted(Comparator.reverseOrder())
                .limit(count).mapToLong(Long::valueOf).reduce(1L, (a, b) -> a * b);
    }
}