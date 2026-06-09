package software.ulpgc.aoc.day03.a;

import software.ulpgc.aoc.day03.common.PowerGrid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day03/input_3.txt"));
            // Inyectamos el calculador de la Parte A (BatteryBank::maxJoltage)
            System.out.println("Total Joltage (Parte A): " + PowerGrid.calculateTotalJoltage(content, BatteryBank::maxJoltage));
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
    }
}