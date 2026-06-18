package software.ulpgc.aoc.day08.common;

import java.util.List;
import java.util.stream.IntStream;

public class UnionFindElectricalGrid implements ElectricalGrid {
    private final int[] parent, size;

    public UnionFindElectricalGrid(int totalBoxes) {
        parent = IntStream.range(0, totalBoxes).toArray();
        size = IntStream.generate(() -> 1).limit(totalBoxes).toArray();
    }

    private int findRoot(int id) {
        return parent[id] == id ? id : (parent[id] = findRoot(parent[id]));
    }

    @Override
    public boolean tryConnecting(JunctionBox a, JunctionBox b) {
        int rootA = findRoot(a.id()), rootB = findRoot(b.id());
        if (rootA == rootB) return false;
        parent[rootA] = rootB; size[rootB] += size[rootA]; return true;
    }

    @Override
    public List<Integer> getCircuitSizes() {
        return IntStream.range(0, parent.length).filter(i -> parent[i] == i)
                .mapToObj(i -> size[i]).toList();
    }
}