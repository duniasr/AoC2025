package software.ulpgc.aoc.day06.b;

import software.ulpgc.aoc.day06.common.MathProblem;
import software.ulpgc.aoc.day06.common.Operator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProblemParser {
    public static MathProblem parse(List<String> blockLines) {
        return new MathProblem(
                extractVerticalNumbers(blockLines.subList(0, blockLines.size() - 1)),
                extractOperator(blockLines.get(blockLines.size() - 1))
        );
    }

    private static List<Long> extractVerticalNumbers(List<String> numberLines) {
        return IntStream.range(0, numberLines.get(0).length())
                .map(col -> numberLines.get(0).length() - 1 - col) // Invertimos (derecha a izquierda)
                .mapToObj(col -> readColumn(numberLines, col))
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong).toList();
    }

    private static String readColumn(List<String> numberLines, int col) {
        return numberLines.stream().map(line -> String.valueOf(line.charAt(col)))
                .collect(Collectors.joining()).trim();
    }

    private static Operator extractOperator(String operatorLine) {
        return Operator.from(operatorLine.trim().charAt(0));
    }
}