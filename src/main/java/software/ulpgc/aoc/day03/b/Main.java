package software.ulpgc.aoc.day03.b;

import software.ulpgc.aoc.day03.EmergencyPowerSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            String notes = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day03/input_3.txt"));
            long totalOutput = EmergencyPowerSystem.from(notes).calculateTotalOutputJoltage(12);
            System.out.println("Total output joltage(12 digits): " + totalOutput);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}