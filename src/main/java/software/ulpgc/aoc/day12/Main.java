package software.ulpgc.aoc.day12;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Regions that fit all presents: " +
                PuzzleReader.readFrom(Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day12/input.txt")).replace("\r", ""))
                        .stream()
                        .filter(TreeRegion::canFitAll)
                        .count());
    }
}