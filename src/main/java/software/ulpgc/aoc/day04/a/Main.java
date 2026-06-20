package software.ulpgc.aoc.day04.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day04/input_4.txt"));

            long result = PaperRollDiagram.from(content).countAccessibleRolls();

            System.out.println("Accessible Rolls: " + result);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}