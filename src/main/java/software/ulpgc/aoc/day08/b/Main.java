package software.ulpgc.aoc.day08.b;

import software.ulpgc.aoc.day08.common.Playground;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> input = Files.readAllLines(Path.of("src/main/java/software/ulpgc/resources/day08/input.txt"));
        long result = Playground.fromLines(input)
                .connectUntilSingleCircuit();
        System.out.println("Result (X coords product of last connection): " + result);
    }
}