package software.ulpgc.aoc.day01.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try (var document = Files.lines(Paths.get("src/main/java/software/ulpgc/resources/day01/input_1.txt"))) {

            int password = SafeDecoder.decodePassword(document.filter(line -> !line.isBlank()));
            System.out.println("The actual password is: " + password);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}