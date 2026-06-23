package software.ulpgc.aoc.day11.a;

import software.ulpgc.aoc.day11.DeviceNetwork;
import software.ulpgc.aoc.day11.PathFinder;

public class PathCounting {
    private final DeviceNetwork network;

    public PathCounting(DeviceNetwork network) {
        this.network = network;
    }

    public long countPathsBetween(String start, String end) {
        return PathFinder.count(network.connections(), start, end);
    }
}
