package software.ulpgc.aoc.day06.a;

import software.ulpgc.aoc.day06.common.MathProblem;
import software.ulpgc.aoc.day06.common.WorksheetNormalizer;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CephalopodDecoder {

    public static long decode(List<String> rawLines) {
        return process(WorksheetNormalizer.padLines(rawLines));
    }

    private static long process(List<String> lines) {
        return createProblemStream(lines, WorksheetSplitter.findDelimiterColumns(lines))
                .mapToLong(MathProblem::solve).sum();
    }

    private static Stream<MathProblem> createProblemStream(List<String> lines, List<Integer> delimiters) {
        return IntStream.range(0, delimiters.size() + 1)
                .mapToObj(i -> WorksheetSplitter.sliceBlock(lines, getStart(delimiters, i), getEnd(delimiters, i, lines.get(0).length())))
                .filter(block -> !block.get(block.size() - 1).trim().isEmpty())
                .map(ProblemParser::parse);
    }

    private static int getStart(List<Integer> delimiters, int index) {
        return index == 0 ? 0 : delimiters.get(index - 1) + 1;
    }

    private static int getEnd(List<Integer> delimiters, int index, int totalWidth) {
        return index == delimiters.size() ? totalWidth : delimiters.get(index);
    }
}