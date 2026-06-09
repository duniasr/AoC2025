package software.ulpgc.aoc.day03.b;

import software.ulpgc.aoc.day03.common.PowerGrid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            // Asumiendo que usas el mismo archivo input_1.txt que en la parte A
            String content = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day03/input_3.txt"));
            // Inyectamos el calculador recursivo de la Parte B (BatteryBank::maxJoltage)
            System.out.println("Total Joltage (Parte B): " + PowerGrid.calculateTotalJoltage(content, BatteryBank::maxJoltage));
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
    }
}