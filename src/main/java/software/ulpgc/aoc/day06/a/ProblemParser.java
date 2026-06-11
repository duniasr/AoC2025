package software.ulpgc.aoc.day06.a;

import software.ulpgc.aoc.day06.common.MathProblem;
import software.ulpgc.aoc.day06.common.Operator;

import java.util.List;

public class ProblemParser {
    public static MathProblem parse(List<String> tokens) {
        return new MathProblem(extractNumbers(tokens), Operator.from(tokens.get(tokens.size() - 1).charAt(0)));
    }

    private static List<Long> extractNumbers(List<String> tokens) {
        return tokens.subList(0, tokens.size() - 1).stream().map(Long::parseLong).toList();
    }
}