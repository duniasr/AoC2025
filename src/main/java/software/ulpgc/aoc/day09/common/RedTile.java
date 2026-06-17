package software.ulpgc.aoc.day09.common;

// Represents a red tile at a specific grid coordinate.
public record RedTile(int x, int y) {
    public long calculateAreaWith(RedTile other) {
        long width = Math.abs(x - other.x) + 1;
        long height = Math.abs(y - other.y) + 1;
        return width * height;
    }
}