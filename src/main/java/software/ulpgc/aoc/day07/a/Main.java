package software.ulpgc.aoc.day07.a;

import software.ulpgc.aoc.day07.TachyonManifold;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            List<String> diagram = Files.readAllLines(Path.of("src/main/java/software/ulpgc/resources/day07/input.txt"));

            long splits = TachyonManifold.fromDiagram(diagram)
                    .simulate(new ClassicalTachyonPhysics())
                    .totalSplits();

            System.out.println("Total tachyon beam splits: " + splits);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}