package software.ulpgc.aoc.day10.b;

import java.util.List;
import java.util.stream.Stream;

public class Parser {
    public static Machine parse(String line) {
        return new Machine(parseJoltage(line), parseSchematics(line));
    }

    private static List<Integer> parseJoltage(String line) {
        return Stream.of(line.substring(line.indexOf("{") + 1, line.indexOf("}")).split(","))
                .map(Integer::parseInt).toList();
    }

    private static List<List<Integer>> parseSchematics(String line) {
        return Stream.of(line.substring(line.indexOf("(") + 1, line.lastIndexOf(")")).split("\\)\\s*\\("))
                .map(b -> Stream.of(b.split(",")).map(Integer::parseInt).toList()).toList();
    }
}