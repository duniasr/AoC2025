package main.java.software.ulpgc.aoc.day02.b;

import main.java.software.ulpgc.aoc.day02.common.RangeProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try (var lines = Files.lines(Paths.get("src/main/java/software/ulpgc/resources/day02/input_2.txt"))) {
            lines.findFirst().ifPresent(line ->
                    System.out.println("Sum of invalid IDs: " + RangeProcessor.sumInvalidIds(line, InvalidIdDetector::isInvalid)));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}