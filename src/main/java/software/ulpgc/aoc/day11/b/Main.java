package software.ulpgc.aoc.day11.b;

import software.ulpgc.aoc.day11.Reactor;
import software.ulpgc.aoc.day11.ReactorReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Reactor network = ReactorReader.readFrom(
                    Files.readAllLines(Paths.get("src/main/java/software/ulpgc/resources/day11/input.txt"))
            );

            long result = new PathCounting(network).countPathsThrough("svr", "out", "dac", "fft");

            System.out.println("Total valid paths: " + result);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}