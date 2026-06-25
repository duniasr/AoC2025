package software.ulpgc.aoc.day04.b;

import software.ulpgc.aoc.day04.Coordinate; // Importamos el common
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PaperRollDiagram {

    private final char[][] grid;

    private PaperRollDiagram(char[][] grid) {
        this.grid = grid;
    }

    public static PaperRollDiagram from(String rawDiagram) {
        char[][] parsedGrid = Arrays.stream(rawDiagram.split("\\R"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
        return new PaperRollDiagram(parsedGrid);
    }

    public boolean isRoll(Coordinate coord) {
        return grid[coord.row()][coord.col()] == '@';
    }

    public void removeRoll(Coordinate coord) {
        grid[coord.row()][coord.col()] = '.';
    }

    public boolean isWithinBounds(Coordinate coord) {
        return coord.row() >= 0 && coord.row() < grid.length &&
                coord.col() >= 0 && coord.col() < grid[0].length;
    }

    public Stream<Coordinate> streamAllCoordinates() {
        return IntStream.range(0, grid.length).boxed()
                .flatMap(row -> IntStream.range(0, grid[0].length)
                        .mapToObj(col -> new Coordinate(row, col)));
    }
}