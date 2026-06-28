package software.ulpgc.aoc.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String input = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day12/input.txt")).replace("\r", "");

            List<TreeRegion> regions = PuzzleReader.readFrom(input);
            PresentsFit presents = PresentsFit.fitLogic();
            long fittingRegions = regions.stream().filter(presents::fitUnderRegion).count();
            System.out.println("Regions that fit all presents: " + fittingRegions);

        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}