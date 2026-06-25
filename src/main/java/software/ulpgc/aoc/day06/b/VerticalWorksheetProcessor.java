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
        int width = block.getFirst().length();
        return new MathProblem(
                IntStream.range(0, width)
                        .map(i -> width - 1 - i) // Inverts the stream to go right-to-left
                        .mapToObj(col -> extractNumberFromColumn(block, col))
                        .filter(str -> !str.isEmpty())
                        .map(Long::parseLong)
                        .toList(),
                Operator.from(block.getLast().replace(" ", "").charAt(0))
        );
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