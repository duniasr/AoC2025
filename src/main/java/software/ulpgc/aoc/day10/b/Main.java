package software.ulpgc.aoc.day10.b;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Total presses: " + Files.lines(Paths.get("src/main/java/software/ulpgc/resources/day10/input.txt"))
                .filter(line -> !line.isBlank())
                .map(Parser::parse)
                .mapToLong(Machine::solve) // ¡Aquí está el cambio clave!
                .sum());
    }
}