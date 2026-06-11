package software.ulpgc.aoc.day08.a;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        var input = Files.readAllLines(Path.of("src/main/java/software/ulpgc/resources/day08/input.txt"));
        System.out.println("Result (Product of top 3 circuits): " + PlaygroundAnalyzer.solve(input, 1000));
    }
}