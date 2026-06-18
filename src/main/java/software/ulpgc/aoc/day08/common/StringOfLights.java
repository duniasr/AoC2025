package software.ulpgc.aoc.day08.common;

// Represents the cable connecting two junction boxes.
public record StringOfLights(JunctionBox boxA, JunctionBox boxB, double length) {
    public static StringOfLights between(JunctionBox a, JunctionBox b) {
        return new StringOfLights(a, b, a.distanceTo(b));
    }
}