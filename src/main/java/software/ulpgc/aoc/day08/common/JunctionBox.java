package software.ulpgc.aoc.day08.common;

import java.util.Arrays;

// Represents a physical junction box in 3D space.
public record JunctionBox(int id, int x, int y, int z) {
    public static JunctionBox parse(int id, String line) {
        int[] coordinates = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
        return new JunctionBox(id, coordinates[0], coordinates[1], coordinates[2]);
    }

    public double distanceTo(JunctionBox targetBox) {
        return Math.sqrt(Math.pow(x - targetBox.x, 2) +
                        Math.pow(y - targetBox.y, 2) +
                        Math.pow(z - targetBox.z, 2));
    }
}