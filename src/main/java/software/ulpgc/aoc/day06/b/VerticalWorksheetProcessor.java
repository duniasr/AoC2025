package software.ulpgc.aoc.day06.b;

import software.ulpgc.aoc.day06.MathProblem;
import software.ulpgc.aoc.day06.Operator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class VerticalWorksheetProcessor {

    static List<String> padToMaxWidth(List<String> lines) {
        int maxWidth = lines.stream().mapToInt(String::length).max().orElse(0);
        return lines.stream()
                .map(line -> String.format("%-" + maxWidth + "s", line))
                .toList();
    }

    static Stream<MathProblem> extractProblems(List<String> lines) {
        List<Integer> delimiters = findDelimiterColumns(lines);

        return IntStream.range(0, delimiters.size() + 1)
                .mapToObj(i -> sliceBlock(lines, getStart(delimiters, i), getEnd(delimiters, i, lines.getFirst().length())))
                .filter(block -> !block.getLast().trim().isEmpty())
                .map(VerticalWorksheetProcessor::parseVerticalProblem);
    }

    private static List<Integer> findDelimiterColumns(List<String> lines) {
        return IntStream.range(0, lines.getFirst().length())
                .filter(col -> lines.stream().allMatch(line -> line.charAt(col) == ' '))
                .boxed()
                .toList();
    }

    private static List<String> sliceBlock(List<String> lines, int start, int end) {
        return lines.stream()
                .map(line -> line.substring(start, end))
                .toList();
    }

    private static MathProblem parseVerticalProblem(List<String> block) {
        String operatorRow = block.getLast();
        char operatorSymbol = operatorRow.replace(" ", "").charAt(0);
        Operator operator = Operator.from(operatorSymbol);

        int blockWidth = block.getFirst().length();

        List<Long> numbers = IntStream.range(0, blockWidth)
                .map(i -> blockWidth - 1 - i) // Inverts the stream to go right-to-left
                .mapToObj(col -> extractNumberFromColumn(block, col))
                .filter(str -> !str.isEmpty())
                .map(Long::parseLong)
                .toList();

        return new MathProblem(numbers, operator);
    }

    private static String extractNumberFromColumn(List<String> block, int col) {
        return IntStream.range(0, block.size() - 1)
                .mapToObj(row -> String.valueOf(block.get(row).charAt(col)))
                .filter(str -> !str.isBlank())
                .collect(Collectors.joining());
    }

    private static int getStart(List<Integer> delimiters, int index) {
        return index == 0 ? 0 : delimiters.get(index - 1) + 1;
    }

    private static int getEnd(List<Integer> delimiters, int index, int totalWidth) {
        return index == delimiters.size() ? totalWidth : delimiters.get(index);
    }
}