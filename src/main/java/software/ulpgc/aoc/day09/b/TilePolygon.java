package software.ulpgc.aoc.day09.b;

import software.ulpgc.aoc.day09.common.RedTile;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TilePolygon {
    private final List<Edge> edges;

    public TilePolygon(List<RedTile> vertices) {
        this.edges = IntStream.range(0, vertices.size())
                .mapToObj(i -> new Edge(vertices.get(i), vertices.get((i + 1) % vertices.size())))
                .toList();
    }

    public boolean containsRectangle(RedTile t1, RedTile t2) {
        int xMin = Math.min(t1.x(), t2.x()), xMax = Math.max(t1.x(), t2.x());
        int yMin = Math.min(t1.y(), t2.y()), yMax = Math.max(t1.y(), t2.y());

        // 1. Cruce de fronteras
        boolean crossesInterior = edges.stream().anyMatch(e ->
                e.isHorizontal()
                        ? (yMin < e.a().y() && e.a().y() < yMax && e.maxX() > xMin && e.minX() < xMax)
                        : (xMin < e.a().x() && e.a().x() < xMax && e.maxY() > yMin && e.minY() < yMax)
        );
        if (crossesInterior) return false;

        // 2. Comprobación de interior
        if (xMin < xMax && yMin < yMax) {
            return isPointInside(xMin + 0.5, yMin + 0.5);
        } else if (xMin == xMax && yMin < yMax) {
            return validateVerticalLine(xMin, yMin, yMax);
        } else if (yMin == yMax && xMin < xMax) {
            return validateHorizontalLine(yMin, xMin, xMax);
        }
        return true;
    }

    private boolean isPointInside(double px, double py) {
        long intersections = edges.stream()
                .filter(Edge::isVertical)
                .filter(e -> e.a().x() > px && e.minY() < py && py < e.maxY())
                .count();
        return intersections % 2 != 0;
    }

    private boolean validateVerticalLine(int x, int yMin, int yMax) {
        List<Integer> yCoords = Stream.concat(Stream.of(yMin, yMax),
                        edges.stream().filter(Edge::isHorizontal).filter(e -> e.minX() <= x && x <= e.maxX()).map(e -> e.a().y()))
                .filter(y -> y >= yMin && y <= yMax).distinct().sorted().toList();

        return IntStream.range(0, yCoords.size() - 1).allMatch(k -> {
            if (yCoords.get(k + 1) - yCoords.get(k) <= 1) return true;
            double py = yCoords.get(k) + 0.5;
            return edges.stream().anyMatch(e -> e.isVertical() && e.a().x() == x && e.minY() <= py && py <= e.maxY())
                    || isPointInside(x + 0.1, py);
        });
    }

    private boolean validateHorizontalLine(int y, int xMin, int xMax) {
        List<Integer> xCoords = Stream.concat(Stream.of(xMin, xMax),
                        edges.stream().filter(Edge::isVertical).filter(e -> e.minY() <= y && y <= e.maxY()).map(e -> e.a().x()))
                .filter(x -> x >= xMin && x <= xMax).distinct().sorted().toList();

        return IntStream.range(0, xCoords.size() - 1).allMatch(k -> {
            if (xCoords.get(k + 1) - xCoords.get(k) <= 1) return true;
            double px = xCoords.get(k) + 0.5;
            return edges.stream().anyMatch(e -> e.isHorizontal() && e.a().y() == y && e.minX() <= px && px <= e.maxX())
                    || isPointInside(px, y + 0.1);
        });
    }
}
