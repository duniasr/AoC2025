package software.ulpgc.aoc.day10.a;

import java.util.List;
import java.util.stream.Stream;

public class Parser {
    public static Machine parse(String line) {
        return new Machine(parseDiagram(line), parseSchematics(line));
    }

    private static List<Integer> parseDiagram(String line) {
        return Stream.of(line.substring(line.indexOf("[") + 1, line.indexOf("]")).split(""))
                .map(s -> s.equals("#") ? 1 : 0).toList();
    }

    private static List<List<Integer>> parseSchematics(String line) {
        return Stream.of(line.substring(line.indexOf("(") + 1, line.lastIndexOf(")")).split("\\)\\s*\\("))
                .map(b -> Stream.of(b.split(",")).map(Integer::parseInt).toList()).toList();
    }
}