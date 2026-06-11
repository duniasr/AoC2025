package software.ulpgc.aoc.day09.a;

import software.ulpgc.aoc.day09.common.RedTile;
import java.util.List;
import java.util.stream.IntStream;

public class MovieTheater {

    // 1. Estado interno (inmutable)
    private final List<RedTile> tiles;

    // 2. Constructor privado: Obliga a usar el método "with" para crear la clase
    private MovieTheater(List<RedTile> tiles) {
        this.tiles = tiles;
    }

    // 3. Static Factory Method (El punto de entrada de la Fluent API)
    public static MovieTheater with(String rawInput) {
        List<RedTile> parsedTiles = rawInput.lines()            // Transforma el String gigante en un Stream de líneas
                .map(String::trim)                              // Limpia espacios
                .filter(line -> !line.isEmpty())                // Ignora líneas vacías
                .map(line -> line.split(","))                   // Separa por comas
                .map(parts -> new RedTile(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                .toList();

        return new MovieTheater(parsedTiles); // Crea y devuelve la instancia
    }

    // 4. Método de acción (Calcula y devuelve el resultado)
    public long largestArea() {
        return IntStream.range(0, tiles.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, tiles.size())
                        .mapToObj(j -> tiles.get(i).areaWith(tiles.get(j))))
                .max(Long::compareTo)
                .orElse(0L);
    }
}