package software.ulpgc.aoc.day08;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Playground {
    private final List<JunctionBox> boxes;
    private final ElectricalGrid grid;

    private Playground(List<JunctionBox> boxes) {
        this.boxes = boxes;
        this.grid = new UnionFindElectricalGrid(boxes.size());
    }

    public static Playground fromLines(List<String> input) {
        return new Playground(IntStream.range(0, input.size())
                .mapToObj(i -> JunctionBox.parse(i, input.get(i))).toList());
    }

    private Stream<StringOfLights> allStrings() {
        return IntStream.range(0, boxes.size()).boxed().flatMap(i ->
                IntStream.range(i + 1, boxes.size()).mapToObj(j -> StringOfLights.between(boxes.get(i), boxes.get(j))));
    }

    // --- PART A METHODS ---

    public Playground connectShortestStrings(int maxCables) {
        allStrings().sorted(Comparator.comparingDouble(StringOfLights::length)).limit(maxCables)
                .forEach(lights -> grid.tryConnecting(lights.boxA(), lights.boxB()));
        return this;
    }

    public long productOfLargestCircuits(int count) {        return grid.getCircuitSizes().stream().sorted(Comparator.reverseOrder())
                .limit(count).mapToLong(Long::valueOf).reduce(1L, (a, b) -> a * b);
    }

    // --- PART B METHODS ---
    // MATH RULE (MST): Connecting N nodes requires exactly N-1 valid edges.
    public long connectUntilSingleCircuit() {
        return allStrings().sorted(Comparator.comparingDouble(StringOfLights::length))
                .filter(lights -> grid.tryConnecting(lights.boxA(), lights.boxB()))
                .skip(boxes.size() - 2).findFirst()
                .map(last -> (long) last.boxA().x() * last.boxB().x()).orElse(0L);
    }
}