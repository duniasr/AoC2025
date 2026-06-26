package software.ulpgc.aoc.day10.a;

import software.ulpgc.aoc.day10.FactoryMachine;
import software.ulpgc.aoc.day10.MachineCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LightConfigurator implements MachineCommand {

    @Override
    public long execute(FactoryMachine machine) {
        return reduceAndSolve(buildSystemMatrix(machine.indicatorLights(), machine.schematics()), 0, 0);
    }

    // Eliminates variables from the matrix using XOR-based row operations.
    private static List<List<Integer>> buildSystemMatrix(List<Integer> indicatorLights, List<List<Integer>> wiringSchematics) {
        return IntStream.range(0, indicatorLights.size()).mapToObj(light ->
                IntStream.range(0, wiringSchematics.size() + 1).map(btn ->
                        btn < wiringSchematics.size() ? (wiringSchematics.get(btn).contains(light) ? 1 : 0) : indicatorLights.get(light)
                ).boxed().collect(Collectors.toCollection(ArrayList::new))
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    private static int reduceAndSolve(List<List<Integer>> matrix, int row, int col) {
        if (row >= matrix.size() || col >= matrix.get(0).size() - 1) return findMinimumTotalPresses(matrix, unconstrainedButtonsOf(matrix));
        return reduceAndSolve(
                eliminateAndReturn(matrix, row, col, pivotRowFor(matrix, col, row)),
                pivotRowFor(matrix, col, row) != -1 ? row + 1 : row,
                col + 1
        );
    }

    private static List<List<Integer>> eliminateAndReturn(List<List<Integer>> matrix, int row, int col, int pivotRow) {
        if (pivotRow != -1) swap(matrix, row, pivotRow);
        if (pivotRow != -1) IntStream.range(0, matrix.size()).filter(i -> i != row && matrix.get(i).get(col) == 1).forEach(i -> applyXor(matrix, i, row));
        return matrix;
    }

    private static int pivotRowFor(List<List<Integer>> matrix, int col, int startRow) {
        return IntStream.range(startRow, matrix.size()).filter(row -> matrix.get(row).get(col) == 1).findFirst().orElse(-1);
    }

    private static void swap(List<List<Integer>> matrix, int r1, int r2) {
        matrix.set(r1, matrix.set(r2, matrix.get(r1))); // Intercambio perfecto sin variable temporal
    }

    private static void applyXor(List<List<Integer>> matrix, int targetRow, int sourceRow) {
        IntStream.range(0, matrix.get(0).size()).forEach(col ->
                matrix.get(targetRow).set(col, matrix.get(targetRow).get(col) ^ matrix.get(sourceRow).get(col)));
    }

    private static List<Integer> unconstrainedButtonsOf(List<List<Integer>> matrix) {
        return IntStream.range(0, matrix.get(0).size() - 1)
                .filter(col -> IntStream.range(0, matrix.size()).noneMatch(row -> isLeadingOne(matrix, row, col)))
                .boxed().toList();
    }

    private static boolean isLeadingOne(List<List<Integer>> matrix, int row, int col) {
        return matrix.get(row).get(col) == 1 && IntStream.range(0, col).allMatch(prevCol -> matrix.get(row).get(prevCol) == 0);
    }

    private static int findMinimumTotalPresses(List<List<Integer>> matrix, List<Integer> unconstrainedButtons) {
        return IntStream.range(0, 1 << unconstrainedButtons.size())
                .map(mask -> evaluatePressesForCombination(matrix, unconstrainedButtons, mask))
                .min().orElse(0);
    }

    private static int evaluatePressesForCombination(List<List<Integer>> matrix, List<Integer> unconstrainedButtons, int mask) {
        return isConfigurationPossible(matrix) ? Integer.bitCount(mask) + constrainedPressesFor(matrix, unconstrainedButtons, mask) : 999999;
    }

    private static int constrainedPressesFor(List<List<Integer>> matrix, List<Integer> unconstrainedButtons, int mask) {
        return (int) IntStream.range(0, matrix.size())
                .filter(row -> IntStream.range(0, matrix.get(0).size() - 1).anyMatch(col -> matrix.get(row).get(col) == 1))
                .map(row -> requiredPressForMachine(matrix, row, unconstrainedButtons, mask))
                .filter(press -> press == 1).count();
    }

    private static int requiredPressForMachine(List<List<Integer>> matrix, int row, List<Integer> unconstrainedButtons, int mask) {
        return IntStream.range(0, unconstrainedButtons.size())
                .map(i -> matrix.get(row).get(unconstrainedButtons.get(i)) * ((mask >> i) & 1))
                .reduce(matrix.get(row).get(matrix.get(0).size() - 1), (a, b) -> a ^ b);
    }

    private static boolean isConfigurationPossible(List<List<Integer>> matrix) {
        return IntStream.range(0, matrix.size()).noneMatch(row -> matrix.get(row).get(matrix.get(0).size() - 1) == 1 &&
                IntStream.range(0, matrix.get(0).size() - 1).allMatch(col -> matrix.get(row).get(col) == 0));
    }
}