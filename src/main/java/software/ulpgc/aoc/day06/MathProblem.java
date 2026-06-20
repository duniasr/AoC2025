package software.ulpgc.aoc.day06;

import java.util.List;

public record MathProblem(List<Long> numbers, Operator operator) {
    public long solve() {
        return operator.apply(numbers.stream());
    }
}