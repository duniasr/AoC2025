package software.ulpgc.aoc.day06.b;

import software.ulpgc.aoc.day06.common.MathProblem;
import software.ulpgc.aoc.day06.common.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// UTILITY: Procesamiento de texto para lectura vertical (Parte B).
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
        // En vertical, respetamos los espacios internos exactos porque leemos columnas
        return lines.stream()
                .map(line -> line.substring(start, end))
                .toList();
    }

    private static MathProblem parseVerticalProblem(List<String> block) {
        List<Long> numbers = new ArrayList<>();
        String operatorRow = block.getLast();

        // Busca el operador ignorando los espacios
        char operatorSymbol = operatorRow.replace(" ", "").charAt(0);
        Operator operator = Operator.from(operatorSymbol);

        int blockWidth = block.getFirst().length();

        // Lee cada columna individualmente de derecha a izquierda (desde blockWidth - 1 hasta 0)
        for (int col = blockWidth - 1; col >= 0; col--) {
            StringBuilder numberBuilder = new StringBuilder();

            // Recorre desde la primera fila hasta la penúltima (ignorando la del operador)
            for (int row = 0; row < block.size() - 1; row++) {
                char c = block.get(row).charAt(col);
                if (c != ' ') {
                    numberBuilder.append(c);
                }
            }

            if (!numberBuilder.isEmpty()) {
                numbers.add(Long.parseLong(numberBuilder.toString()));
            }
        }

        return new MathProblem(numbers, operator);
    }

    private static int getStart(List<Integer> delimiters, int index) {
        return index == 0 ? 0 : delimiters.get(index - 1) + 1;
    }

    private static int getEnd(List<Integer> delimiters, int index, int totalWidth) {
        return index == delimiters.size() ? totalWidth : delimiters.get(index);
    }
}