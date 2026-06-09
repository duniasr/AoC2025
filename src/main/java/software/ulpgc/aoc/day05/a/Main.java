package software.ulpgc.aoc.day05.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day05/input.txt"));
            System.out.println("Fresh ingredients: " + InventoryAnalyzer.countFreshIngredients(content));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}