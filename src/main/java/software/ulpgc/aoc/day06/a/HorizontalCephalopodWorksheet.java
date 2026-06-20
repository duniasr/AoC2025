package software.ulpgc.aoc.day06.a;

import software.ulpgc.aoc.day06.CephalopodWorksheet;
import software.ulpgc.aoc.day06.MathProblem;

import java.util.List;

public class HorizontalCephalopodWorksheet implements CephalopodWorksheet {

    private final List<String> lines;

    private HorizontalCephalopodWorksheet(List<String> lines) {
        this.lines = lines;
    }

    public static CephalopodWorksheet from(List<String> rawLines) {
        return new HorizontalCephalopodWorksheet(rawLines);
    }

    @Override
    public CephalopodWorksheet unroll() {
        return new HorizontalCephalopodWorksheet(HorizontalWorksheetProcessor.padToMaxWidth(lines));
    }

    @Override
    public long calculateGrandTotal() {
        return HorizontalWorksheetProcessor.extractProblems(lines)
                .mapToLong(MathProblem::solve)
                .sum();
    }
}