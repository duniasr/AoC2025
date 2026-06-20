package software.ulpgc.aoc.day07.b;

import software.ulpgc.aoc.day07.TachyonManifold;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> diagram = Files.readAllLines(Path.of("src/main/java/software/ulpgc/resources/day07/input.txt"));

        long quantumTimelines = TachyonManifold.fromDiagram(diagram)
                .simulate(new QuantumTachyonPhysics())
                .totalActiveTimelines();
        System.out.println("Active timelines: " + quantumTimelines);
    }
}