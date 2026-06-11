package software.ulpgc.aoc.day08.a;

import software.ulpgc.aoc.day08.common.*;
import java.util.*;
import java.util.stream.*;

public class PlaygroundAnalyzer {
    public record Connection(JunctionBox a, JunctionBox b, double dist) {}

    public static long solve(List<String> input, int limit) {
        List<JunctionBox> boxes = parse(input);
        CircuitManager manager = new CircuitManager(boxes.size());

        calculateAllConnections(boxes).stream()
                .sorted(Comparator.comparingDouble(Connection::dist))
                .limit(limit)
                .forEach(c -> manager.union(c.a().id(), c.b().id()));

        return manager.getCircuitSizes().stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToLong(Long::valueOf)
                .reduce(1, (a, b) -> a * b);
    }

    private static List<JunctionBox> parse(List<String> input) {
        return IntStream.range(0, input.size())
                .mapToObj(i -> {
                    String[] parts = input.get(i).split(",");
                    return new JunctionBox(i, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                }).toList();
    }

    private static List<Connection> calculateAllConnections(List<JunctionBox> boxes) {
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                connections.add(new Connection(boxes.get(i), boxes.get(j), boxes.get(i).distanceTo(boxes.get(j))));
            }
        }
        return connections;
    }
}