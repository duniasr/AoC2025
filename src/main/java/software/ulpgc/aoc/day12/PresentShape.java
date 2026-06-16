package software.ulpgc.aoc.day12;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public record PresentShape(List<List<Point>> orientations) {

    public static PresentShape from(List<Point> base) {
        return new PresentShape(Stream.concat(rotations(base), rotations(base.stream().map(Point::flip).toList()))
                .map(PresentShape::normalize).distinct().toList());
    }

    private static Stream<List<Point>> rotations(List<Point> b) {
        return Stream.of(b, rot(b, 1), rot(b, 2), rot(b, 3));
    }

    private static List<Point> rot(List<Point> b, int times) {
        return times == 0 ? b : rot(b.stream().map(Point::rotate).toList(), times - 1);
    }

    private static List<Point> normalize(List<Point> s) {
        return s.stream().map(p -> new Point(p.x() - min(s, true), p.y() - min(s, false)))
                .sorted(Comparator.comparingInt(Point::x).thenComparingInt(Point::y)).toList();
    }

    private static int min(List<Point> s, boolean isX) {
        return s.stream().mapToInt(p -> isX ? p.x() : p.y()).min().orElse(0);
    }
}