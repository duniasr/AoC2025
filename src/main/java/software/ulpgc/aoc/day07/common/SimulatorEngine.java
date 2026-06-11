package software.ulpgc.aoc.day07.common;

import java.util.List;
import java.util.function.BiFunction;

public class SimulatorEngine {
    public static <S> S run(List<String> grid, S initialState, BiFunction<S, String, S> processor) {
        return grid.stream().reduce(initialState, processor, (s1, s2) -> s1);
    }
}