package software.ulpgc.aoc.day11.b;

import software.ulpgc.aoc.day11.DeviceReader;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Total valid paths: " + DeviceReader.readFrom(Files.readAllLines(Paths.get("src/main/java/software/ulpgc/resources/day11/input.txt")))
                .countPathsThrough("svr", "out", "dac", "fft"));
    }
}