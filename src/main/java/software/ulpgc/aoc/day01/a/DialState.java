package main.java.software.ulpgc.aoc.day01.a;

import main.java.software.ulpgc.aoc.day01.common.Rotation;

public record DialState(int position, int zeroCount) {

    public DialState applyRotation(Rotation rotation) {
        return new DialState(
                getFinalPosition(rotation.direction(), rotation.distance()),
                zeroCount + getZeroPassages(rotation.direction(), rotation.distance())
        );
    }
    // Averigua en qué número se va a detener la aguja al terminar el salto
    private int getFinalPosition(char dir, int steps) {
        return (dir == 'R') ? (position + steps) % 100 : (position - steps % 100 + 100) % 100;
    }
    // Miramos si el sitio exacto donde se detuvo la aguja es el cero.
    private int getZeroPassages(char dir, int steps) {
        return getFinalPosition(dir, steps) == 0 ? 1 : 0;
    }
}