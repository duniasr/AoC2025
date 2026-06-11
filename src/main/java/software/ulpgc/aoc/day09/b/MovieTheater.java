package software.ulpgc.aoc.day09.b;

import software.ulpgc.aoc.day09.common.RedTile;
import java.util.List;
import java.util.stream.IntStream;

public class MovieTheater {
    private final List<RedTile> tiles;
    private final TilePolygon polygon;

    private MovieTheater(List<RedTile> tiles) {
        this.tiles = tiles;
        this.polygon = new TilePolygon(tiles);
    }

    public static MovieTheater with(String rawInput) {
        List<RedTile> parsedTiles = rawInput.lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(line -> line.split(","))
                .map(parts -> new RedTile(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                .toList();

        return new MovieTheater(parsedTiles);
    }

    public long largestValidArea() {
        return IntStream.range(0, tiles.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, tiles.size())
                        .mapToObj(j -> new RedTile[]{tiles.get(i), tiles.get(j)}))
                // ¡AQUÍ ESTÁ LA CLAVE! Filtramos los que se salen del polígono
                .filter(pair -> polygon.containsRectangle(pair[0], pair[1]))
                .mapToLong(pair -> pair[0].areaWith(pair[1]))
                .max()
                .orElse(0L);
    }
}
