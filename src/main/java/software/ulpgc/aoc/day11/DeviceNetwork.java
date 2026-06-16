package software.ulpgc.aoc.day11;

import java.util.List;
import java.util.Map;

// Represents an immutable Directed Acyclic Graph (DAG) of devices.
public record DeviceNetwork(Map<String, List<String>> connections) {
    // Resolves Part 1: Counts simple paths using Depth-First Search.
    public long countPathsBetween(String start, String end) {
        return PathFinder.count(connections, start, end);
    }

    // Resolves Part 2: Calculates total valid paths
    public long countPathsThrough(String start, String end, String via1, String via2) {
        return (countPathsBetween(start, via1) * countPathsBetween(via1, via2) * countPathsBetween(via2, end)) +
                (countPathsBetween(start, via2) * countPathsBetween(via2, via1) * countPathsBetween(via1, end));
    }
}