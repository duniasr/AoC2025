package software.ulpgc.aoc.day01;

public record Rotation(char direction, int distance) {

    public static Rotation from(String instruction) {
        return new Rotation(instruction.charAt(0), Integer.parseInt(instruction.substring(1)));
    }
}