package software.ulpgc.aoc.day12;

public record Point(int x, int y) {
    public Point rotate() { return new Point(-y, x); }
    public Point flip() { return new Point(-x, y); }
}