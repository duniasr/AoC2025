package software.ulpgc.aoc.day01.b;

import software.ulpgc.aoc.day01.Rotation;
import java.util.stream.IntStream;

public record Dial(int currentPosition, int passwordScore) {

    public static Dial initial() {
        return new Dial(50, 0);
    }

    public Dial rotate(Rotation rotation) {
        return new Dial(calculateNextPosition(rotation),this.passwordScore + (int) countZeroPassages(rotation));
    }

    private int calculateNextPosition(Rotation rotation) {
        return rotation.direction() == 'R'
                ? (currentPosition + rotation.distance()) % 100
                : ((currentPosition - rotation.distance()) % 100 + 100) % 100;
    }

    private long countZeroPassages(Rotation rotation) {
        return IntStream.rangeClosed(1, rotation.distance())
                .map(step -> rotation.direction() == 'R'
                        ? (currentPosition + step) % 100 : ((currentPosition - step) % 100 + 100) % 100)
                .filter(pos -> pos == 0)
                .count();
    }
}