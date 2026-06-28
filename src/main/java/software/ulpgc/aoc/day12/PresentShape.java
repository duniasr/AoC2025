package software.ulpgc.aoc.day12;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public record PresentShape(List<List<Point>> orientations) {

    public static PresentShape from(List<Point> base) {
        return new PresentShape(Stream.concat(rotations(base), rotations(flipped(base)))
                .map(PresentShape::normalize).distinct().toList());
    }

    public int size() {
        return orientations.get(0).size();
    }

    private static Stream<List<Point>> rotations(List<Point> basePoints) {
        return Stream.of(basePoints, rot(basePoints, 1), rot(basePoints, 2), rot(basePoints, 3));
    }

    private static List<Point> rot(List<Point> basePoints, int times) {
        return times == 0 ? basePoints : rot(basePoints.stream().map(Point::rotate).toList(), times - 1);
    }
    
    private static List<Point> flipped(List<Point> base) {
        return base.stream().map(Point::flip).toList();
    }

    private static List<Point> normalize(List<Point> shapePoints) {
        int dx = min(shapePoints, Point::x), dy = min(shapePoints, Point::y);
        return shapePoints.stream().map(point -> new Point(point.x() - dx, point.y() - dy))
                .sorted(Comparator.comparingInt(Point::x).thenComparingInt(Point::y)).toList();
    }

    private static int min(List<Point> shapePoints, ToIntFunction<Point> axis) {
        return shapePoints.stream().mapToInt(axis).min().orElse(0);
    }
}