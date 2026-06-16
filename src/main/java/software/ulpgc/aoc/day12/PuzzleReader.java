package software.ulpgc.aoc.day12;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PuzzleReader {

    public static List<TreeRegion> readFrom(String text) {
        return parseRegions(text.split("\n\n")[text.split("\n\n").length - 1].split("\n"), parseShapes(text.split("\n\n")));
    }

    private static List<PresentShape> parseShapes(String[] blocks) {
        return Arrays.stream(blocks).limit(blocks.length - 1).map(PuzzleReader::parseShape).toList();
    }

    private static PresentShape parseShape(String block) {
        return PresentShape.from(IntStream.range(1, block.split("\n").length).boxed()
                .flatMap(y -> parseRow(block.split("\n")[y], y - 1)).toList());
    }

    private static Stream<Point> parseRow(String row, int y) {
        return IntStream.range(0, row.length()).filter(x -> row.charAt(x) == '#').mapToObj(x -> new Point(x, y));
    }

    private static List<TreeRegion> parseRegions(String[] lines, List<PresentShape> shapes) {
        return Arrays.stream(lines).filter(l -> !l.isBlank()).map(l -> parseRegion(l, shapes)).toList();
    }

    private static TreeRegion parseRegion(String line, List<PresentShape> shapes) {
        return new TreeRegion(Integer.parseInt(line.split(":")[0].split("x")[0]), Integer.parseInt(line.split(":")[0].split("x")[1]),
                parsePresents(line.split(":")[1].trim().split("\\s+"), shapes));
    }

    private static List<PresentShape> parsePresents(String[] counts, List<PresentShape> shapes) {
        return IntStream.range(0, counts.length).boxed()
                .flatMap(i -> Collections.nCopies(Integer.parseInt(counts[i]), shapes.get(i)).stream()).toList();
    }
}