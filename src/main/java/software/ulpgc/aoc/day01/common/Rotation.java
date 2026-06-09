package main.java.software.ulpgc.aoc.day01.common;

public record Rotation(char direction, int distance) {
    public static Rotation fromText(String line) {
        return new Rotation(line.charAt(0), Integer.parseInt(line.substring(1)));
    }
}