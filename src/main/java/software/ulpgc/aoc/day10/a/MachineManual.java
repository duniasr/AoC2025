package software.ulpgc.aoc.day10.a;

import java.util.Arrays;
import java.util.List;

public class MachineManual {

    public static FactoryMachine readMachineFrom(String line) {
        return new FactoryMachine(extractIndicatorLights(line), extractWiringSchematics(line));
    }

    private static List<Integer> extractIndicatorLights(String line) {
        return Arrays.stream(textInsideBrackets(line).split(""))
                .map(MachineManual::mapToLightStatus)
                .toList();
    }

    private static List<List<Integer>> extractWiringSchematics(String line) {
        return Arrays.stream(textInsideParentheses(line).split("\\)\\s*\\("))
                .map(MachineManual::extractWiredTargets)
                .toList();
    }

    private static String textInsideBrackets(String line) {
        return line.substring(line.indexOf("[") + 1, line.indexOf("]"));
    }

    private static String textInsideParentheses(String line) {
        return line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"));
    }

    private static int mapToLightStatus(String symbol) {
        return symbol.equals("#") ? 1 : 0;
    }

    private static List<Integer> extractWiredTargets(String block) {
        return Arrays.stream(block.split(",")).map(Integer::parseInt).toList();
    }
}