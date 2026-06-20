package software.ulpgc.aoc.day04.a;

import software.ulpgc.aoc.day04.Coordinate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PaperRollDiagram {

    private final String[] grid;

    private PaperRollDiagram(String[] grid) {
        this.grid = grid;
    }

    public static PaperRollDiagram from(String rawDiagram) {
        return new PaperRollDiagram(rawDiagram.split("\\R"));
    }

    public long countAccessibleRolls() {
        return streamAllCoordinates()
                .filter(this::isRoll)
                .filter(this::isAccessibleByForklift)
                .count();
    }

    private boolean isAccessibleByForklift(Coordinate coord) {
        return coord.streamAdjacent()
                .filter(this::isWithinBounds)
                .filter(this::isRoll)
                .count() < 4;
    }

    private boolean isRoll(Coordinate coord) {
        return grid[coord.row()].charAt(coord.col()) == '@';
    }

    private boolean isWithinBounds(Coordinate coord) {
        return coord.row() >= 0 && coord.row() < grid.length &&
                coord.col() >= 0 && coord.col() < grid[0].length();
    }

    private Stream<Coordinate> streamAllCoordinates() {
        return IntStream.range(0, grid.length).boxed()
                .flatMap(row -> IntStream.range(0, grid[0].length())
                        .mapToObj(col -> new Coordinate(row, col)));
    }
}