package software.ulpgc.aoc.day06.common;

import java.util.List;

public class WorksheetNormalizer {
    public static List<String> padLines(List<String> lines) {
        return padToWidth(lines, lines.stream().mapToInt(String::length).max().orElse(0));
    }

    private static List<String> padToWidth(List<String> lines, int width) {
        return lines.stream().map(line -> String.format("%-" + width + "s", line)).toList();
    }
}