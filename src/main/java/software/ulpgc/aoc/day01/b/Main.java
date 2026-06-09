package main.java.software.ulpgc.aoc.day01.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try (var lines = Files.lines(Paths.get("src/main/java/software/ulpgc/resources/day01/input_1.txt"))) {
            System.out.println("The password is: " + SafeDecoder.decodePassword(lines.filter(l -> !l.isBlank())));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}