package software.ulpgc.aoc.day08.b;

import software.ulpgc.aoc.day08.common.*;
import java.util.*;
import java.util.stream.*;

public class PlaygroundAnalyzer {
    public record Connection(JunctionBox a, JunctionBox b, double dist) {}

    public static long solve(List<String> input) {
        List<JunctionBox> boxes = parse(input);
        CircuitManager manager = new CircuitManager(boxes.size());

        // Usamos un array de un elemento para "hackear" la mutabilidad dentro del stream
        long[] result = {0};

        calculateAllConnections(boxes).stream()
                .sorted(Comparator.comparingDouble(Connection::dist))
                .forEach(c -> {
                    if (manager.find(c.a().id()) != manager.find(c.b().id())) {
                        manager.union(c.a().id(), c.b().id());
                        if (manager.getCircuitSizes().size() == 1 && result[0] == 0) {
                            result[0] = (long) c.a().x() * c.b().x();
                        }
                    }
                });
        return result[0];
    }

    private static List<JunctionBox> parse(List<String> input) {
        return IntStream.range(0, input.size())
                .mapToObj(i -> {
                    String[] p = input.get(i).split(",");
                    return new JunctionBox(i, Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));
                }).toList();
    }

    private static List<Connection> calculateAllConnections(List<JunctionBox> boxes) {
        return IntStream.range(0, boxes.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, boxes.size())
                        .mapToObj(j -> new Connection(boxes.get(i), boxes.get(j), boxes.get(i).distanceTo(boxes.get(j)))))
                .collect(Collectors.toList());
    }
}