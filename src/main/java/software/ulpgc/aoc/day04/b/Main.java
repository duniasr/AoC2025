package software.ulpgc.aoc.day04.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("src/main/java/software/ulpgc/resources/day04/input_4.txt"));
            System.out.println("Rollos retirados en total (Parte B): " + PaperGrid.countTotalRemovals(content));
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
    }
}