package software.ulpgc.aoc.day12;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PresentFitter {

    public static boolean fit(List<List<Set<Integer>>> remainingMasks, Set<Integer> occupied, Set<Set<Integer>> visited, AtomicInteger nodes) {
        if (hasGivenUp(nodes)) return false;
        if (isVictory(remainingMasks)) return true;
        if (isKnownFailure(visited, occupied)) return false;

        return currentOptionsFor(remainingMasks)
                .filter(mask -> canPlace(occupied, mask))
                .anyMatch(mask -> fit(remaining(remainingMasks), placed(occupied, mask), visited, nodes));
    }

    public static Stream<Set<Integer>> generateMasks(TreeRegion region, PresentShape shape) {
        return shape.orientations().stream()
                .flatMap(orientation -> allPositionsIn(region, orientation))
                .filter(mask -> !mask.isEmpty());
    }

    private static boolean hasGivenUp(AtomicInteger nodes) {
        return nodes.incrementAndGet() > 50000;
    }

    private static boolean isVictory(List<?> masks) {
        return masks.isEmpty();
    }

    private static boolean isKnownFailure(Set<Set<Integer>> visited, Set<Integer> occupied) {
        return !visited.add(occupied);
    }

    private static Stream<Set<Integer>> currentOptionsFor(List<List<Set<Integer>>> masks) {
        return masks.get(0).stream();
    }

    private static List<List<Set<Integer>>> remaining(List<List<Set<Integer>>> masks) {
        return masks.subList(1, masks.size());
    }

    private static boolean canPlace(Set<Integer> occupied, Set<Integer> mask) {
        return Collections.disjoint(occupied, mask);
    }

    private static Set<Integer> placed(Set<Integer> occupied, Set<Integer> mask) {
        Set<Integer> next = new HashSet<>(occupied);
        next.addAll(mask);
        return next;
    }


    private static Stream<Set<Integer>> allPositionsIn(TreeRegion region, List<Point> shape) {
        return IntStream.range(0, region.width() * region.height())
                .mapToObj(i -> maskAt(region, shape, i % region.width(), i / region.width()));
    }

    private static Set<Integer> maskAt(TreeRegion region, List<Point> shape, int dx, int dy) {
        return isInside(region, shape, dx, dy)
                ? shape.stream().map(p -> to1D(region.width(), p.x() + dx, p.y() + dy)).collect(Collectors.toSet())
                : Set.of();
    }

    private static boolean isInside(TreeRegion region, List<Point> shape, int dx, int dy) {
        return shape.stream().allMatch(p -> p.x() + dx >= 0 && p.x() + dx < region.width() && p.y() + dy >= 0 && p.y() + dy < region.height());
    }

    private static int to1D(int width, int x, int y) {
        return y * width + x;
    }
}