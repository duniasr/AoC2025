package software.ulpgc.aoc.day09.b;

import software.ulpgc.aoc.day09.common.RedTile;

public record Edge(RedTile a, RedTile b) {
    public boolean isVertical() { return a.x() == b.x(); }
    public boolean isHorizontal() { return a.y() == b.y(); }
    public int minX() { return Math.min(a.x(), b.x()); }
    public int maxX() { return Math.max(a.x(), b.x()); }
    public int minY() { return Math.min(a.y(), b.y()); }
    public int maxY() { return Math.max(a.y(), b.y()); }
}