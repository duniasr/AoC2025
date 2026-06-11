package software.ulpgc.aoc.day06.common;

import java.util.function.LongBinaryOperator;
import java.util.stream.Stream;

public enum Operator {
    ADD('+', Long::sum, 0L),
    MULTIPLY('*', (a, b) -> a * b, 1L);

    private final char symbol;
    private final LongBinaryOperator operation;
    private final long identity;

    Operator(char symbol, LongBinaryOperator operation, long identity) {
        this.symbol = symbol;
        this.operation = operation;
        this.identity = identity;
    }

    public static Operator from(char symbol) {
        return Stream.of(values()).filter(o -> o.symbol == symbol).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Operador inválido: " + symbol));
    }

    public long apply(Stream<Long> numbers) {
        return numbers.mapToLong(Long::longValue).reduce(identity, operation);
    }
}