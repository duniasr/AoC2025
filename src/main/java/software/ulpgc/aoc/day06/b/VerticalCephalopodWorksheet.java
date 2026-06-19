package software.ulpgc.aoc.day06.b;

import software.ulpgc.aoc.day06.common.CephalopodWorksheet;
import software.ulpgc.aoc.day06.common.MathProblem;

import java.util.List;

public class VerticalCephalopodWorksheet implements CephalopodWorksheet {

    private final List<String> lines;

    private VerticalCephalopodWorksheet(List<String> lines) {
        this.lines = lines;
    }

    public static CephalopodWorksheet from(List<String> rawLines) {
        return new VerticalCephalopodWorksheet(rawLines);
    }

    @Override
    public CephalopodWorksheet unroll() {
        return new VerticalCephalopodWorksheet(VerticalWorksheetProcessor.padToMaxWidth(lines));
    }

    @Override
    public long calculateGrandTotal() {
        return VerticalWorksheetProcessor.extractProblems(lines)
                .mapToLong(MathProblem::solve)
                .sum();
    }
}