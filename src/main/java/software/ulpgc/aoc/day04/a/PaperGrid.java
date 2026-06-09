package main.java.software.ulpgc.aoc.day04.a;

import java.util.stream.IntStream;

public class PaperGrid {

    // Transforma el input en un grid 2D y cuenta los rollos accesibles totales.
    public static long countAccessibleRolls(String fullInput) {
        String[] grid = fullInput.split("\n");

        // IntStream externo (barre las filas 'y')
        return IntStream.range(0, grid.length)
                // IntStream interno (barre las columnas 'x' de cada fila)
                .mapToLong(row -> IntStream.range(0, grid[row].length())
                        .filter(col -> ForkliftAnalyzer.isAccessible(grid, row, col))
                        .count()) // Suma cuántos accesibles hay en esta fila
                .sum(); // Suma el total de todas las filas
    }
}