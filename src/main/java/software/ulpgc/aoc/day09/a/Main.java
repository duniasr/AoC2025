package software.ulpgc.aoc.day09.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            String input = Files.readString(Path.of("src/main/java/software/ulpgc/resources/day09/input.txt"));
            System.out.println("Largest area between tiles: " + MovieTheater.from(input).findLargestRectangleArea());
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}