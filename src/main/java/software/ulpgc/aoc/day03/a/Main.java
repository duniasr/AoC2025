package software.ulpgc.aoc.day03.a;

import software.ulpgc.aoc.day03.EmergencyPowerSystem;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        String notes = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day03/input_3.txt"));

        long totalOutput = EmergencyPowerSystem.from(notes).calculateTotalOutputJoltage(2);

        System.out.println("Total output joltage(2 digits): " + totalOutput);
    }
}