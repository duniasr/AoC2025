package software.ulpgc.aoc.day06.a;

import java.util.List;
import java.util.stream.IntStream;

public class WorksheetSplitter {
    public static List<Integer> findDelimiterColumns(List<String> lines) {
        return IntStream.range(0, lines.get(0).length()).filter(col -> isColumnEmpty(lines, col)).boxed().toList();
    }

    private static boolean isColumnEmpty(List<String> lines, int col) {
        return lines.stream().allMatch(line -> line.charAt(col) == ' ');
    }

    public static List<String> sliceBlock(List<String> lines, int start, int end) {
        return lines.stream().map(line -> line.substring(start, end).trim()).filter(s -> !s.isEmpty()).toList();
    }
}