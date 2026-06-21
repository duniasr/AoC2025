package software.ulpgc.aoc.day05.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            String databaseContent = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day05/input.txt"));
            long partBResult = InventoryDatabase.from(databaseContent)
                    .calculateTotalFreshCapacity();
            System.out.println("Total fresh ingredients capacity: " + partBResult);

        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}