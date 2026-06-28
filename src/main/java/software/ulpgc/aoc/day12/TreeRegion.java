package software.ulpgc.aoc.day12;

import java.util.List;

public record TreeRegion(int width, int height, List<PresentShape> presents) {
    public int area() { return width * height; }
}