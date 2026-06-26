package software.ulpgc.aoc.day10.b;

import software.ulpgc.aoc.day10.FactoryMachine;
import software.ulpgc.aoc.day10.MachineCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class JoltageConfigurator implements MachineCommand {

    @Override
    public long execute(FactoryMachine machine) {
        return findMinimumPresses(machine.joltageTargets(), machine.schematics(), new HashMap<>());
    }

    private static long findMinimumPresses(List<Integer> targets, List<List<Integer>> schematics, Map<List<Integer>, Long> memo) {
        if (isFullyConfigured(targets)) return 0;
        if (memo.containsKey(targets)) return memo.get(targets);

        long minPresses = IntStream.range(0, 1 << schematics.size())
                .filter(mask -> isConfigurationFeasible(targets, schematics, mask))
                .mapToLong(mask -> calculateTotalCost(targets, schematics, mask, memo))
                .min().orElse(999999999999L);

        memo.put(targets, minPresses);
        return minPresses;
    }

    private static boolean isFullyConfigured(List<Integer> targets) {
        return targets.stream().allMatch(voltage -> voltage == 0);
    }

    private static boolean isConfigurationFeasible(List<Integer> targets, List<List<Integer>> schematics, int mask) {
        return IntStream.range(0, targets.size()).allMatch(i -> {
            int impact = calculateButtonImpact(schematics, mask, i);
            return targets.get(i) >= impact && (targets.get(i) - impact) % 2 == 0;
        });
    }

    private static int calculateButtonImpact(List<List<Integer>> schematics, int mask, int counterIndex) {
        return (int) IntStream.range(0, schematics.size())
                .filter(btnIdx -> ((mask >> btnIdx) & 1) == 1 && schematics.get(btnIdx).contains(counterIndex))
                .count();
    }

    private static long calculateTotalCost(List<Integer> targets, List<List<Integer>> schematics, int mask, Map<List<Integer>, Long> memo) {
        return Integer.bitCount(mask) + 2 * findMinimumPresses(nextJoltageState(targets, schematics, mask), schematics, memo);
    }

    private static List<Integer> nextJoltageState(List<Integer> targets, List<List<Integer>> schematics, int mask) {
        return IntStream.range(0, targets.size())
                .map(i -> (targets.get(i) - calculateButtonImpact(schematics, mask, i)) / 2)
                .boxed().toList();
    }
}