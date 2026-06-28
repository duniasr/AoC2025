package software.ulpgc.aoc.day12;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PuzzleReader {

    public static List<TreeRegion> readFrom(String text) {
        return parse(text.split("\n\n"));
    }

    private static List<TreeRegion> parse(String[] blocks) {
        return parseRegionsFrom(blocks[blocks.length - 1], parseShapesFrom(blocks));
    }

    private static List<PresentShape> parseShapesFrom(String[] blocks) {
        return Arrays.stream(blocks).limit(blocks.length - 1)
                .map(PuzzleReader::parseSingleShape).toList();
    }

    private static PresentShape parseSingleShape(String block) {
        return PresentShape.from(IntStream.range(1, block.split("\n").length).boxed()
                .flatMap(rowIdx -> pointsInRow(block.split("\n")[rowIdx], rowIdx - 1)).toList());
    }

    private static Stream<Point> pointsInRow(String rowText, int y) {
        return IntStream.range(0, rowText.length()).filter(colIdx -> rowText.charAt(colIdx) == '#')
                .mapToObj(colIdx -> new Point(colIdx, y));
    }

    private static List<TreeRegion> parseRegionsFrom(String regionsBlock, List<PresentShape> shapes) {
        return Arrays.stream(regionsBlock.split("\n")).filter(line -> !line.isBlank())
                .map(line -> parseSingleRegion(line, shapes)).toList();
    }

    private static TreeRegion parseSingleRegion(String line, List<PresentShape> shapes) {
        String[] dims = line.split(":")[0].split("x");
        return new TreeRegion(Integer.parseInt(dims[0]), Integer.parseInt(dims[1]), extractPresents(line.split(":")[1].trim(), shapes));
    }

    private static List<PresentShape> extractPresents(String countsText, List<PresentShape> shapes) {
        String[] counts = countsText.split("\\s+");
        return IntStream.range(0, counts.length).boxed()
                .flatMap(index -> Collections.nCopies(Integer.parseInt(counts[index]), shapes.get(index)).stream()).toList();
    }
}