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

    public long removeAllAccessibleRolls() {
        return recursiveRemove(0);
    }

    private long recursiveRemove(long accumulatedTotal) {
        List<Coordinate> toRemove = streamAllCoordinates()
                .filter(this::isRoll)
                .filter(this::isAccessibleByForklift)
                .toList();

        if (toRemove.isEmpty()) return accumulatedTotal;

        toRemove.forEach(this::removeRoll);
        return recursiveRemove(accumulatedTotal + toRemove.size());
    }

    private boolean isAccessibleByForklift(Coordinate target) {
        return target.streamAdjacent()
                .filter(this::isWithinBounds)
                .filter(this::isRoll)
                .count() < 4;
    }

    private boolean isRoll(Coordinate coord) {
        return grid[coord.row()][coord.col()] == '@';
    }

    private void removeRoll(Coordinate coord) {
        grid[coord.row()][coord.col()] = '.';
    }

    private boolean isWithinBounds(Coordinate coord) {
        return coord.row() >= 0 && coord.row() < grid.length &&
                coord.col() >= 0 && coord.col() < grid[0].length;
    }

    private Stream<Coordinate> streamAllCoordinates() {
        return IntStream.range(0, grid.length).boxed()
                .flatMap(row -> IntStream.range(0, grid[0].length)
                        .mapToObj(col -> new Coordinate(row, col)));
    }
}