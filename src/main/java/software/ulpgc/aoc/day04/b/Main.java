package software.ulpgc.aoc.day04.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day04/input_4.txt"));

            PaperRollDiagram diagram = PaperRollDiagram.from(content);
            Forklift forklift = new Forklift();
            long totalRemoved = forklift.removeAllAccessibleRolls(diagram);

            System.out.println("Total removed rolls: " + totalRemoved);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}