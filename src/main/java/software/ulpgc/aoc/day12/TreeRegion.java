package software.ulpgc.aoc.day12;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record TreeRegion(int width, int height, List<PresentShape> presents) {
    public boolean canFitAll() {
        int treeArea = width * height;
        int minArea = presents.stream().mapToInt(p -> p.orientations().get(0).size()).sum();
        int maxArea = presents.stream().mapToInt(p -> p.orientations().get(0).stream().mapToInt(Point::x).max().orElse(0) * p.orientations().get(0).stream().mapToInt(Point::y).max().orElse(0)).sum();

        if (minArea > treeArea) return false;
        if (maxArea < treeArea) return true;

        return PresentFitter.fit(presents.stream()
                .sorted((a, b) -> Integer.compare(b.orientations().get(0).size(), a.orientations().get(0).size()))
                .map(shape -> PresentFitter.generateMasks(this, shape).toList())
                .toList(), Set.of(), new HashSet<>());
    }
}