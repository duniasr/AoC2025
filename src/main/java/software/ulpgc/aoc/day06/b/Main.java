package software.ulpgc.aoc.day06.b;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> rawWorksheet = Files.readAllLines(
                Paths.get("src/main/java/software/ulpgc/resources/day06/input.txt")
        );

        long grandTotal = VerticalCephalopodWorksheet.from(rawWorksheet)
                .unroll()
                .calculateGrandTotal();

        System.out.println("The grand total is: " + grandTotal);
    }
}