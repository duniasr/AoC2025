package software.ulpgc.aoc.day11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathFinder {
    public static long count(Map<String, List<String>> graph, String current, String target) {
        return trace(graph, current, target, new HashMap<>());
    }
    // Core DFS algorithm with memoization
    private static long trace(Map<String, List<String>> graph, String curr, String target, Map<String, Long> memo) {
        if (curr.equals(target)) return 1;
        return memo.containsKey(curr) ? memo.get(curr) : memoize(memo, curr,
                graph.getOrDefault(curr, List.of()).stream().mapToLong(next -> trace(graph, next, target, memo)).sum());
    }
    // Helper method to store and return the value simultaneously
    private static long memoize(Map<String, Long> memo, String key, long value) {
        memo.put(key, value);
        return value;
    }
}