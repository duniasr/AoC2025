package software.ulpgc.aoc.day11;

import java.util.List;
import java.util.Map;

public record DeviceNetwork(Map<String, List<String>> connections) {}