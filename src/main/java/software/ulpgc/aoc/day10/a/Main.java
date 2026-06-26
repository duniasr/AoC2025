package software.ulpgc.aoc.day10.a;

import software.ulpgc.aoc.day10.MachineCommand;
import software.ulpgc.aoc.day10.MachineManual;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        MachineCommand command = new LightConfigurator();
        try {
            System.out.println("Total presses: " + Files.lines(Paths.get("src/main/java/software/ulpgc/resources/day10/input.txt"))
                    .map(MachineManual::readMachineFrom)
                    .mapToLong(command::execute)
                    .sum());
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}