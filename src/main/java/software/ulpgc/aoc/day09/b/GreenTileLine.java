package software.ulpgc.aoc.day09.b;

import software.ulpgc.aoc.day09.common.RedTile;

public record GreenTileLine(RedTile start, RedTile end) {

    public boolean isVertical() { return start.x() == end.x(); }
    public boolean isHorizontal() { return start.y() == end.y(); }
    public int minX() { return Math.min(start.x(), end.x()); }
    public int maxX() { return Math.max(start.x(), end.x()); }
    public int minY() { return Math.min(start.y(), end.y()); }
    public int maxY() { return Math.max(start.y(), end.y()); }

    public boolean cutsThrough(int minX, int maxX, int minY, int maxY) {
        return isHorizontal()
                ? (start.y() > minY && start.y() < maxY && maxX() > minX && minX() < maxX)
                : (start.x() > minX && start.x() < maxX && maxY() > minY && minY() < maxY);
    }
}