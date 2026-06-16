package software.ulpgc.aoc.day12;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PresentFitter {

    public static boolean fit(List<List<Set<Integer>>> remainingMasks, Set<Integer> occupied, Set<Set<Integer>> visited) {
        if (remainingMasks.isEmpty()) return true;
        return visited.add(occupied) && remainingMasks.get(0).stream()
                .filter(mask -> occupied.stream().noneMatch(mask::contains))
                .anyMatch(mask -> fit(remainingMasks.subList(1, remainingMasks.size()),
                        Stream.concat(occupied.stream(), mask.stream()).collect(Collectors.toSet()), visited));
    }

    public static Stream<Set<Integer>> generateMasks(TreeRegion r, PresentShape shape) {
        return shape.orientations().stream().flatMap(or -> IntStream.range(0, r.width() * r.height())
                .mapToObj(i -> mask(r, or, i % r.width(), i / r.width()))).filter(s -> !s.isEmpty());
    }

    private static Set<Integer> mask(TreeRegion r, List<Point> shape, int dx, int dy) {
        return shape.stream().allMatch(p -> p.x() + dx >= 0 && p.x() + dx < r.width() && p.y() + dy >= 0 && p.y() + dy < r.height())
                ? shape.stream().map(p -> (p.y() + dy) * r.width() + (p.x() + dx)).collect(Collectors.toSet())
                : Set.of();
    }
}