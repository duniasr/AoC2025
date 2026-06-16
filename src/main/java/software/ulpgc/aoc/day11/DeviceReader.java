package software.ulpgc.aoc.day11;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceReader {
    // Builds the DAG map
    public static DeviceNetwork readFrom(List<String> lines) {
        return new DeviceNetwork(lines.stream().filter(l -> !l.isBlank())
                .collect(Collectors.toMap(l -> l.split(":")[0].trim(), DeviceReader::extractOutputs)));
    }
    // Extracts target devices.
    private static List<String> extractOutputs(String line) {
        return line.split(":").length > 1 && !line.split(":")[1].isBlank()
                ? List.of(line.split(":")[1].trim().split("\\s+")) : List.of();
    }
}