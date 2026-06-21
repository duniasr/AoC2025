package software.ulpgc.aoc.day10.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            long totalPresses = Files.lines(Paths.get("src/main/java/software/ulpgc/resources/day10/input.txt"))
                    .filter(line -> !line.isBlank())
                    .map(MachineManual::readMachineFrom)
                    .mapToLong(FactoryMachine::minimumJoltagePresses)
                    .sum();

            System.out.println("Total joltage configuration presses: " + totalPresses);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}