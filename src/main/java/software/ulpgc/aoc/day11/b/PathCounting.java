package software.ulpgc.aoc.day11.b;

import software.ulpgc.aoc.day11.DeviceNetwork;
import software.ulpgc.aoc.day11.PathFinder;

public class PathCounting {
    private final DeviceNetwork network;

    public PathCounting(DeviceNetwork network) {
        this.network = network;
    }

    public long countPathsThrough(String start, String end, String via1, String via2) {
        long route1 = PathFinder.count(network.connections(), start, via1) * PathFinder.count(network.connections(), via1, via2) * PathFinder.count(network.connections(), via2, end);
        long route2 = PathFinder.count(network.connections(), start, via2) * PathFinder.count(network.connections(), via2, via1) * PathFinder.count(network.connections(), via1, end);
        return route1 + route2;
    }
}
