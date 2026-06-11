package software.ulpgc.aoc.day07.a;

import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Total tachyon beam splits: " + TachyonManifold.calculate(Files.readAllLines(Path.of("src/main/java/software/ulpgc/resources/day07/input.txt"))));
    }
}