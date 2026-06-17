package software.ulpgc.aoc.day12;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public record TreeRegion(int width, int height, List<PresentShape> presents) {
    public boolean canFitAll() {
        if (minPresentsArea() > area()) return false;
        if (maxBoundingBoxArea() <= area()) return true;
        return solveWithFitter();
    }

    private int area() {
        return width * height;
    }

    private int minPresentsArea() {
        return presents.stream()
                .mapToInt(p -> p.orientations().get(0).size())
                .sum();
    }

    private int maxBoundingBoxArea() {
        return presents.stream()
                .mapToInt(this::boundingAreaOf)
                .sum();
    }

    private int boundingAreaOf(PresentShape p) {
        List<Point> shape = p.orientations().get(0);
        int w = shape.stream().mapToInt(Point::x).max().orElse(0) + 1;
        int h = shape.stream().mapToInt(Point::y).max().orElse(0) + 1;
        return w * h;
    }

    private boolean solveWithFitter() {
        List<List<Set<Integer>>> masks = presents.stream()
                .sorted((a, b) -> Integer.compare(b.orientations().get(0).size(), a.orientations().get(0).size()))
                .map(shape -> PresentFitter.generateMasks(this, shape).toList())
                .toList();

        return PresentFitter.fit(masks, Set.of(), new HashSet<>(), new AtomicInteger(0));
    }
}