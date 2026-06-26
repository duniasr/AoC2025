package software.ulpgc.aoc.day11.a;

import software.ulpgc.aoc.day11.Reactor;
import software.ulpgc.aoc.day11.PathFinder;

public class PathCounting {
    private final Reactor network;

    public PathCounting(Reactor network) {
        this.network = network;
    }

    public long countPathsBetween(String start, String end) {
        return PathFinder.count(network.connections(), start, end);
    }
}
