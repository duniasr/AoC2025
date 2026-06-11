package software.ulpgc.aoc.day06.b;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("The grand total is: " + CephalopodDecoder.decode(
                Files.readAllLines(Paths.get("src/main/java/software/ulpgc/resources/day06/input.txt"))
        ));
    }
}