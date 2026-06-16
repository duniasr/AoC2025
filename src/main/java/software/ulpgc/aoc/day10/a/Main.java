package software.ulpgc.aoc.day10.a;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Total presses: " + Files.lines(Paths.get("src/main/java/software/ulpgc/resources/day10/input.txt"))
                .map(Parser::parse)
                .mapToInt(Machine::solve)
                .sum());
    }
}