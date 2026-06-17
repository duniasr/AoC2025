package software.ulpgc.aoc.day09.b;

import software.ulpgc.aoc.day09.common.RedTile;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GreenTileLoop {
    private final List<GreenTileLine> lines;

    public GreenTileLoop(List<RedTile> redTiles) {
        this.lines = IntStream.range(0, redTiles.size())
                .mapToObj(i -> new GreenTileLine(redTiles.get(i), redTiles.get((i + 1) % redTiles.size())))
                .toList();
    }
    public boolean enclosesRectangleBetween(RedTile corner1, RedTile corner2) {
        int minX = Math.min(corner1.x(), corner2.x());
        int maxX = Math.max(corner1.x(), corner2.x());
        int minY = Math.min(corner1.y(), corner2.y());
        int maxY = Math.max(corner1.y(), corner2.y());

        return !isCrossedByAnyLine(minX, maxX, minY, maxY) && isInteriorContained(minX, maxX, minY, maxY);
    }

    private boolean isCrossedByAnyLine(int minX, int maxX, int minY, int maxY) {
        return lines.stream().anyMatch(line -> line.cutsThrough(minX, maxX, minY, maxY));
    }

    private boolean isInteriorContained(int minX, int maxX, int minY, int maxY) {
        if (minX < maxX && minY < maxY) return isPointInside(minX + 0.5, minY + 0.5);
        if (minX == maxX && minY < maxY) return isVerticalSegmentInside(minX, minY, maxY);
        if (minY == maxY && minX < maxX) return isHorizontalSegmentInside(minY, minX, maxX);

        return true;
    }

    // --- LOGIC OF VERTICAL SEGMENTS ---

    private boolean isVerticalSegmentInside(int x, int minY, int maxY) {
        List<Integer> yCoords = getIntersectingYCoordinates(x, minY, maxY);
        return IntStream.range(0, yCoords.size() - 1)
                .allMatch(k -> isValidVerticalSubSegment(x, yCoords.get(k), yCoords.get(k + 1)));
    }

    private List<Integer> getIntersectingYCoordinates(int x, int minY, int maxY) {
        return Stream.concat(Stream.of(minY, maxY),
                        lines.stream()
                                .filter(GreenTileLine::isHorizontal)
                                .filter(line -> line.minX() <= x && x <= line.maxX())
                                .map(line -> line.start().y()))
                .filter(y -> y >= minY && y <= maxY)
                .distinct().sorted().toList();
    }

    private boolean isValidVerticalSubSegment(int x, int y1, int y2) {
        if (y2 - y1 <= 1) return true; // No hay hueco real que comprobar
        double midY = y1 + 0.5;
        boolean liesOnBoundary = lines.stream().anyMatch(line ->
                line.isVertical() && line.start().x() == x && line.minY() <= midY && midY <= line.maxY());

        return liesOnBoundary || isPointInside(x + 0.1, midY);
    }

    // --- LOGIC OF HORIZONTAL SEGMENTS ---

    private boolean isHorizontalSegmentInside(int y, int minX, int maxX) {
        List<Integer> xCoords = getIntersectingXCoordinates(y, minX, maxX);
        return IntStream.range(0, xCoords.size() - 1)
                .allMatch(k -> isValidHorizontalSubSegment(y, xCoords.get(k), xCoords.get(k + 1)));
    }

    private List<Integer> getIntersectingXCoordinates(int y, int minX, int maxX) {
        return Stream.concat(Stream.of(minX, maxX),
                        lines.stream()
                                .filter(GreenTileLine::isVertical)
                                .filter(line -> line.minY() <= y && y <= line.maxY())
                                .map(line -> line.start().x()))
                .filter(x -> x >= minX && x <= maxX)
                .distinct().sorted().toList();
    }

    private boolean isValidHorizontalSubSegment(int y, int x1, int x2) {
        if (x2 - x1 <= 1) return true; // No hay hueco real que comprobar
        double midX = x1 + 0.5;
        boolean liesOnBoundary = lines.stream().anyMatch(line ->
                line.isHorizontal() && line.start().y() == y && line.minX() <= midX && midX <= line.maxX());

        return liesOnBoundary || isPointInside(midX, y + 0.1);
    }

    // --- RAY CASTING ---
    private boolean isPointInside(double px, double py) {
        long intersections = lines.stream()
                .filter(GreenTileLine::isVertical)
                .filter(line -> line.start().x() > px && line.minY() < py && py < line.maxY())
                .count();
        return intersections % 2 != 0;
    }
}