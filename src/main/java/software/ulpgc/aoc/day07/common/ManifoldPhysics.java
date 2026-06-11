package software.ulpgc.aoc.day07.common;

import java.util.List;

public class ManifoldPhysics {
    public static boolean isSplitter(String row, int pos) {
        return row.charAt(pos) == '^';
    }

    public static boolean isWithinBounds(String row, int pos) {
        return pos >= 0 && pos < row.length();
    }

    public static int findStart(List<String> grid) {
        return grid.getFirst().indexOf('S');
    }
}