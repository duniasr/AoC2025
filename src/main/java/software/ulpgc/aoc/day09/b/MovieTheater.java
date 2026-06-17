package software.ulpgc.aoc.day09.b;

import software.ulpgc.aoc.day09.common.RedTile;
import java.util.List;
import java.util.stream.IntStream;

public class MovieTheater {
    private final List<RedTile> redTiles;
    private final GreenTileLoop greenLoop;

    private MovieTheater(List<RedTile> redTiles) {
        this.redTiles = redTiles;
        this.greenLoop = new GreenTileLoop(redTiles);
    }

    public static MovieTheater from(String input) {
        List<RedTile> parsedTiles = input.lines()
                .filter(line -> !line.isBlank())
                .map(MovieTheater::parseTile)
                .toList();

        return new MovieTheater(parsedTiles);
    }

    private static RedTile parseTile(String line) {
        String[] parts = line.split(",");
        return new RedTile(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public long findLargestValidRectangleArea() {
        return IntStream.range(0, redTiles.size()).boxed()
                .flatMapToLong(i -> IntStream.range(i + 1, redTiles.size())
                        .filter(j -> greenLoop.enclosesRectangleBetween(redTiles.get(i), redTiles.get(j)))
                        .mapToLong(j -> redTiles.get(i).calculateAreaWith(redTiles.get(j)))
                )
                .max()
                .orElse(0L);
    }
}