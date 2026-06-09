package software.ulpgc.aoc.day04.b;

import java.util.List;
import java.util.stream.IntStream;

public class PaperGrid {

    // Puerta de entrada: Convierte el texto en array e inicia la película.
    public static long countTotalRemovals(String fullInput) {
        return simulateRemovals(fullInput.split("\n"));
    }

    // Recursividad inmutable: Busca, cuenta y llama al siguiente fotograma.
    private static long simulateRemovals(String[] grid) {
        List<Point> targets = ForkliftAnalyzer.findRemovable(grid);
        return targets.isEmpty() ? 0 : targets.size() + simulateRemovals(applyRemovals(grid, targets));
    }

    // Crea un fotograma (mapa) completamente nuevo aplicando los borrados de este turno.
    private static String[] applyRemovals(String[] grid, List<Point> targets) {
        return IntStream.range(0, grid.length)
                .mapToObj(r -> updateRow(grid[r], r, targets))
                .toArray(String[]::new);
    }

    // Cambia los caracteres '@' por '.' de una fila específica de forma segura.
    private static String updateRow(String row, int rowIndex, List<Point> targets) {
        char[] chars = row.toCharArray();
        targets.stream().filter(p -> p.r() == rowIndex).forEach(p -> chars[p.c()] = '.');
        return new String(chars);
    }
}