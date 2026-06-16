package software.ulpgc.aoc.day10.a;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {

    public static int calculate(List<Integer> target, List<List<Integer>> buttons) {
        return reduceAndSolve(buildMatrix(target, buttons), 0, 0);
    }

    private static List<List<Integer>> buildMatrix(List<Integer> target, List<List<Integer>> buttons) {
        return IntStream.range(0, target.size()).mapToObj(light ->
                IntStream.range(0, buttons.size() + 1).map(btn ->
                        btn < buttons.size() ? (buttons.get(btn).contains(light) ? 1 : 0) : target.get(light)
                ).boxed().collect(Collectors.toCollection(ArrayList::new))
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    // 1. Gauss-Jordan Funcional
    private static int reduceAndSolve(List<List<Integer>> matrix, int r, int c) {
        if (r >= matrix.size() || c >= matrix.get(0).size() - 1) return findMinWeight(matrix, getFreeCols(matrix));
        return reduceAndSolve(processAndReturn(matrix, r, c, findPivot(matrix, c, r)), findPivot(matrix, c, r) != -1 ? r + 1 : r, c + 1);
    }

    private static List<List<Integer>> processAndReturn(List<List<Integer>> matrix, int r, int c, int pivot) {
        if (pivot != -1) swapRows(matrix, r, pivot);
        if (pivot != -1) IntStream.range(0, matrix.size()).filter(i -> i != r && matrix.get(i).get(c) == 1).forEach(i -> xorRows(matrix, i, r));
        return matrix;
    }

    private static int findPivot(List<List<Integer>> matrix, int c, int startRow) {
        return IntStream.range(startRow, matrix.size()).filter(r -> matrix.get(r).get(c) == 1).findFirst().orElse(-1);
    }

    private static void swapRows(List<List<Integer>> matrix, int r1, int r2) {
        matrix.set(r1, matrix.set(r2, matrix.get(r1))); // Intercambio perfecto sin variable temporal
    }

    private static void xorRows(List<List<Integer>> matrix, int target, int source) {
        IntStream.range(0, matrix.get(0).size()).forEach(c ->
                matrix.get(target).set(c, matrix.get(target).get(c) ^ matrix.get(source).get(c)));
    }

    // 2. Extraer el Espacio Nulo (Variables libres)
    private static List<Integer> getFreeCols(List<List<Integer>> matrix) {
        return IntStream.range(0, matrix.get(0).size() - 1)
                .filter(c -> IntStream.range(0, matrix.size()).noneMatch(r -> isLeadingOne(matrix, r, c)))
                .boxed().toList();
    }

    private static boolean isLeadingOne(List<List<Integer>> matrix, int r, int c) {
        return matrix.get(r).get(c) == 1 && IntStream.range(0, c).allMatch(prev -> matrix.get(r).get(prev) == 0);
    }

    // 3. Explorar todas las realidades posibles para hallar el mínimo
    private static int findMinWeight(List<List<Integer>> matrix, List<Integer> freeCols) {
        return IntStream.range(0, 1 << freeCols.size()).map(mask -> evaluateWeight(matrix, freeCols, mask)).min().orElse(0);
    }

    private static int evaluateWeight(List<List<Integer>> matrix, List<Integer> freeCols, int mask) {
        return isSystemValid(matrix) ? Integer.bitCount(mask) + getBoundWeight(matrix, freeCols, mask) : 999999;
    }

    private static int getBoundWeight(List<List<Integer>> matrix, List<Integer> freeCols, int mask) {
        return (int) IntStream.range(0, matrix.size()).filter(r -> IntStream.range(0, matrix.get(0).size() - 1).anyMatch(c -> matrix.get(r).get(c) == 1))
                .map(r -> calculateBoundValue(matrix, r, freeCols, mask)).filter(val -> val == 1).count();
    }

    private static int calculateBoundValue(List<List<Integer>> matrix, int r, List<Integer> freeCols, int mask) {
        return IntStream.range(0, freeCols.size()).map(i -> matrix.get(r).get(freeCols.get(i)) * ((mask >> i) & 1))
                .reduce(matrix.get(r).get(matrix.get(0).size() - 1), (a, b) -> a ^ b);
    }

    private static boolean isSystemValid(List<List<Integer>> matrix) {
        return IntStream.range(0, matrix.size()).noneMatch(r -> matrix.get(r).get(matrix.get(0).size() - 1) == 1 &&
                IntStream.range(0, matrix.get(0).size() - 1).allMatch(c -> matrix.get(r).get(c) == 0));
    }
}