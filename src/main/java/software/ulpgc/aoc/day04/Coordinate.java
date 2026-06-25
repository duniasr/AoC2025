package software.ulpgc.aoc.day04;

import java.util.stream.Stream;

public record Coordinate(int row, int col) {
    public Stream<Coordinate> streamAdjacentCoordinates() {
        return Stream.of(
                new Coordinate(row - 1, col - 1),
                new Coordinate(row - 1, col),
                new Coordinate(row - 1, col + 1),
                new Coordinate(row, col - 1),
                new Coordinate(row, col + 1),
                new Coordinate(row + 1, col - 1),
                new Coordinate(row + 1, col),
                new Coordinate(row + 1, col + 1)
        );
    }
}