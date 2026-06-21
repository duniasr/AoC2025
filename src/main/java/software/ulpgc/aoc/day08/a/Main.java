package software.ulpgc.aoc.day08.a;

import software.ulpgc.aoc.day08.Playground;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            List<String> input = Files.readAllLines(Path.of("src/main/java/software/ulpgc/resources/day08/input.txt"));
            long result = Playground.fromLines(input)
                    .connectShortestStrings(1000)
                    .productOfLargestCircuits(3);
            System.out.println("Result (Product of top 3 circuits): " + result);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}