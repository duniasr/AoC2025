package software.ulpgc.aoc.day01.a;

import software.ulpgc.aoc.day01.Rotation;

public record Dial(int currentPosition, int passwordScore) {

    public static Dial initial() {
        return new Dial(50, 0);
    }

    public Dial rotate(Rotation rotation) {
        return buildNextDial(calculateNextPosition(rotation));
    }

    private Dial buildNextDial(int targetPosition) {
        return new Dial(
                targetPosition,
                this.passwordScore + (targetPosition == 0 ? 1 : 0)
        );
    }

    private int calculateNextPosition(Rotation rotation) {
        return rotation.direction() == 'R'
                ? (currentPosition + rotation.distance()) % 100
                : ((currentPosition - rotation.distance()) % 100 + 100) % 100;
    }
}