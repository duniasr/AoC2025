package software.ulpgc.aoc.day12;

import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PresentsFitLogic implements PresentsFit {

    @Override
    public boolean fitUnderRegion(TreeRegion region) {
        return minArea(region) <= region.area() && (region.presents().size() <= (region.width() / 3) * (region.height() / 3) || solve(region));
    }

    private int minArea(TreeRegion region) {
        return region.presents().stream().mapToInt(PresentShape::size).sum();
    }

    private boolean solve(TreeRegion region) {
        return fit(region.presents().stream().sorted((shape1, shape2) -> shape2.size() - shape1.size()).map(shape -> generateMasks(region, shape).toList()).toList(), new BitSet(), new HashSet<>());
    }

    private boolean fit(List<List<BitSet>> remaining, BitSet occupied, Set<BitSet> visited) {
        return (remaining.isEmpty() || !visited.add(occupied)) ? remaining.isEmpty() : remaining.get(0).stream().filter(mask -> !occupied.intersects(mask)).anyMatch(mask -> fit(remaining.subList(1, remaining.size()), placed(occupied, mask), visited));
    }

    private BitSet placed(BitSet occupied, BitSet mask) {
        return Stream.of((BitSet) occupied.clone()).peek(bitset -> bitset.or(mask)).findFirst().get();
    }

    private Stream<BitSet> generateMasks(TreeRegion region, PresentShape shape) {
        return shape.orientations().stream().flatMap(orientation -> allPositionsIn(region, orientation)).filter(mask -> !mask.isEmpty());
    }

    private Stream<BitSet> allPositionsIn(TreeRegion region, List<Point> shape) {
        return IntStream.range(0, region.area()).mapToObj(index -> maskAt(region, shape, index % region.width(), index / region.width()));
    }

    private BitSet maskAt(TreeRegion region, List<Point> shapePoints, int dx, int dy) {
        return isInside(region, shapePoints, dx, dy) ? bitSetFrom(region.width(), shapePoints, dx, dy) : new BitSet();
    }

    private BitSet bitSetFrom(int width, List<Point> shapePoints, int dx, int dy) {
        return Stream.of(new BitSet()).peek(bitset -> shapePoints.forEach(point -> bitset.set((point.y() + dy) * width + point.x() + dx))).findFirst().get();
    }

    private boolean isInside(TreeRegion region, List<Point> shapePoints, int dx, int dy) {
        return shapePoints.stream().allMatch(point -> point.x() + dx >= 0 && point.x() + dx < region.width() && point.y() + dy >= 0 && point.y() + dy < region.height());
    }
}
