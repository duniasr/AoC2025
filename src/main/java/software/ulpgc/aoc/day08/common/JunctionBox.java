package software.ulpgc.aoc.day08.common;

public record JunctionBox(int id, int x, int y, int z) {
    public double distanceTo(JunctionBox other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
    }
}