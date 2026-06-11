package software.ulpgc.aoc.day08.common;

import java.util.*;

public class CircuitManager {
    private final int[] parent;
    private final int[] size;

    public CircuitManager(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]); // Path compression (optimización clave)
    }

    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
            size[rootJ] += size[rootI];
        }
    }

    public Collection<Integer> getCircuitSizes() {
        Map<Integer, Integer> circuitSizes = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) circuitSizes.put(i, size[i]);
        }
        return circuitSizes.values();
    }
}