package software.ulpgc.aoc.day04.a;

import java.util.Arrays;

public class ForkliftAnalyzer {

    // Las 8 direcciones posibles (dy, dx) para buscar vecinos en 2D.
    private static final int[][] DIRS = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};

    // Evalúa si en la coordenada indicada hay un rollo accesible (< 4 vecinos).
    public static boolean isAccessible(String[] grid, int row, int col) {
        if (grid[row].charAt(col) != '@') return false; // Si no es un rollo, no nos interesa
        return countNeighbors(grid, row, col) < 4;
    }

    // Cuenta cuántos rollos '@' hay alrededor de una coordenada específica.
    private static long countNeighbors(String[] grid, int row, int col) {
        return Arrays.stream(DIRS)
                .filter(dir -> isValid(grid, row + dir[0], col + dir[1]))
                .filter(dir -> grid[row + dir[0]].charAt(col + dir[1]) == '@') // Contamos si es rollo
                .count();
    }

    // Comprueba que las coordenadas no se salgan de los bordes del mapa.
    private static boolean isValid(String[] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length();
    }
}