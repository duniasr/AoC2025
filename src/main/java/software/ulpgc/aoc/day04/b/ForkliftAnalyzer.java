package main.java.software.ulpgc.aoc.day04.b;

import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ForkliftAnalyzer {

    private static final int[][] DIRS = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};

    // Barre todo el mapa y devuelve la lista de coordenadas de los rollos listos para retirar.
    public static List<Point> findRemovable(String[] grid) {
        return IntStream.range(0, grid.length).boxed().flatMap(r ->
                IntStream.range(0, grid[r].length())
                        .filter(c -> isAccessible(grid, r, c))
                        .mapToObj(c -> new Point(r, c))
        ).toList();
    }

    private static boolean isAccessible(String[] grid, int row, int col) {
        if (grid[row].charAt(col) != '@') return false;
        return countNeighbors(grid, row, col) < 4;
    }

    private static long countNeighbors(String[] grid, int row, int col) {
        return Arrays.stream(DIRS)
                .filter(dir -> isValid(grid, row + dir[0], col + dir[1]))
                .filter(dir -> grid[row + dir[0]].charAt(col + dir[1]) == '@')
                .count();
    }

    private static boolean isValid(String[] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length();
    }
}