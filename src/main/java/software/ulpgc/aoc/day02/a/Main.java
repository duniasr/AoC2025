package software.ulpgc.aoc.day02.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try (var lines = Files.lines(Paths.get("src/main/java/software/ulpgc/resources/day02/input_2.txt"))) {

            lines.findFirst().ifPresent(line -> {
                long result = GiftShopDatabase.sumInvalidIds(GiftShopDatabase.from(line));
                System.out.println("Sum of invalid IDs: " + result);
            });

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}