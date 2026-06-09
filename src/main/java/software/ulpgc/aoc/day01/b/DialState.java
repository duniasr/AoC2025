package software.ulpgc.aoc.day01.b;

import main.java.software.ulpgc.aoc.day01.common.Rotation;
import java.util.stream.IntStream;

public record DialState(int position, int zeroCount) {

    public DialState applyRotation(Rotation rotation) {
        return new DialState(
                getFinalPosition(rotation.direction(), rotation.distance()),
                zeroCount + (int) getZeroPassages(rotation.direction(), rotation.distance())
        );
    }
    // Averigua en qué número se va a detener la aguja al terminar el movimiento.
    private int getFinalPosition(char dir, int steps) {
        return (dir == 'R') ? (position + steps) % 100 : (position - steps % 100 + 100) % 100;
    }
    // Cuenta cuántas veces la aguja tocó el cero levantando un Stream temporal de pasos.
    private long getZeroPassages(char dir, int steps) {
        return IntStream.rangeClosed(1, steps)
                .map(step -> (dir == 'R') ? (position + step) % 100 : (position - step + 100 * steps) % 100)
                .filter(pos -> pos == 0)
                .count();
    }
}