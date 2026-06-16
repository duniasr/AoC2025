package software.ulpgc.aoc.day10.b;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Solver {

    public static long calculate(List<Integer> target, List<List<Integer>> buttons) {
        return findMin(target, buttons, new HashMap<>());
    }

    private static long findMin(List<Integer> target, List<List<Integer>> buttons, Map<List<Integer>, Long> memo) {
        if (target.stream().allMatch(t -> t == 0)) return 0;
        return memo.containsKey(target) ? memo.get(target) : memoize(memo, target, IntStream.range(0, 1 << buttons.size())
                .filter(m -> isValid(target, buttons, m)).mapToLong(m -> Integer.bitCount(m) + 2 * findMin(nextState(target, buttons, m), buttons, memo)).min().orElse(999999999999L));
    }

    private static boolean isValid(List<Integer> target, List<List<Integer>> buttons, int mask) {
        // Un estado es válido si al restarle la máscara, TODOS los contadores son pares (divisibles por 2)
        return IntStream.range(0, target.size()).allMatch(i ->
                target.get(i) >= applyMask(buttons, mask, i) && (target.get(i) - applyMask(buttons, mask, i)) % 2 == 0);
    }

    private static int applyMask(List<List<Integer>> buttons, int mask, int light) {
        // Calcula cuántas veces se pulsa una luz específica según la combinación binaria actual
        return (int) IntStream.range(0, buttons.size()).filter(b -> ((mask >> b) & 1) == 1 && buttons.get(b).contains(light)).count();
    }

    private static List<Integer> nextState(List<Integer> target, List<List<Integer>> buttons, int mask) {
        // El salto cuántico: restamos la máscara y DIVIDIMOS TODO ENTRE 2
        return IntStream.range(0, target.size()).map(i -> (target.get(i) - applyMask(buttons, mask, i)) / 2).boxed().toList();
    }

    private static long memoize(Map<List<Integer>, Long> memo, List<Integer> key, long value) {
        memo.put(key, value);
        return value;
    }
}