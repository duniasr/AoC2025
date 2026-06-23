package software.ulpgc.aoc.day11.a;

import software.ulpgc.aoc.day11.DeviceNetwork;
import software.ulpgc.aoc.day11.DeviceReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            DeviceNetwork network = DeviceReader.readFrom(
                    Files.readAllLines(Paths.get("src/main/java/software/ulpgc/resources/day11/input.txt"))
            );

            long result = new PathCounting(network).countPathsBetween("you", "out");

            System.out.println("Total paths: " + result);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}