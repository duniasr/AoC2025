package software.ulpgc.aoc.day05.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            String databaseContent = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day05/input.txt"));
            long freshCount = InventoryDatabase.from(databaseContent)
                    .countFreshAvailableIngredients();

            System.out.println("Fresh ingredients: " + freshCount);

        } catch (IOException e) {
            System.err.println("Error reading the Elves' database: " + e.getMessage());
        }
    }
}