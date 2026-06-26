package software.ulpgc.aoc.day10.b;

import software.ulpgc.aoc.day10.MachineCommand;
import software.ulpgc.aoc.day10.MachineManual;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        MachineCommand command = new JoltageConfigurator();
        try {
            long totalPresses = Files.lines(Paths.get("src/main/java/software/ulpgc/resources/day10/input.txt"))
                    .filter(line -> !line.isBlank())
                    .map(MachineManual::readMachineFrom)
                    .mapToLong(command::execute)
                    .sum();

            System.out.println("Total joltage configuration presses: " + totalPresses);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}