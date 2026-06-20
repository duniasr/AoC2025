package software.ulpgc.aoc.day06;

public interface CephalopodWorksheet {

    // Normalizes the worksheet so all rows have the exact same width
    CephalopodWorksheet unroll();

    // Parses the unrolled worksheet, solves all problems, and sums them up
    long calculateGrandTotal();
}