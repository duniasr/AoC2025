package software.ulpgc.aoc.day10.b;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MachineManual {
    public static FactoryMachine readMachineFrom(String line) {
        return new FactoryMachine(extractJoltageTargets(line), extractWiringSchematics(line));
    }

    private static List<Integer> extractJoltageTargets(String line) {
        return Arrays.stream(textInsideKeys(line).split(","))
                .map(Integer::parseInt)
                .toList();
    }

    private static List<List<Integer>> extractWiringSchematics(String line) {
        return Arrays.stream(textInsideParentheses(line).split("\\)\\s*\\("))
                .map(MachineManual::extractWiredTargets)
                .toList();
    }

    private static String textInsideKeys(String line) {
        return line.substring(line.indexOf("{") + 1, line.indexOf("}"));
    }

    private static String textInsideParentheses(String line) {
        return line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"));
    }

    private static List<Integer> extractWiredTargets(String block) {
        return Arrays.stream(block.split(",")).map(Integer::parseInt).toList();
    }
}