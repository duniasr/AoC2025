package test.Day06;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day06.a.ProblemParser;
import software.ulpgc.aoc.day06.a.WorksheetSplitter;
import software.ulpgc.aoc.day06.b.CephalopodDecoder;
import software.ulpgc.aoc.day06.common.Operator;
import software.ulpgc.aoc.day06.common.WorksheetNormalizer;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class aTest {

    public final static String worksheet = """
                                           123 328  51 64 \s
                                            45 64  387 23 \s
                                             6 98  215 314\s
                                           *   +   *   +  \s
                                           """;

    @Test
    void shouldCalculateGrandTotalCorrectly() {
        assertThat(CephalopodDecoder.decode(worksheet.lines().toList())).isEqualTo(4277556L);
    }

    @Test
    void shouldFindEmptyDelimiterColumns() {
        // Al usar \s, el bloque mide 16 caracteres, generando una 4ª columna vacía en el índice 15.
        assertThat(WorksheetSplitter.findDelimiterColumns(getNormalizedExampleLines())).containsExactly(3, 7, 11, 15);
    }

    @Test
    void shouldApplyMathOperationsCorrectly() {
        assertThat(Operator.from('+').apply(Stream.of(64L, 23L, 314L))).isEqualTo(401L);
        assertThat(Operator.from('*').apply(Stream.of(123L, 45L, 6L))).isEqualTo(33210L);
    }

    @Test
    void shouldParseAndSolveIndividualProblemBlock() {
        assertThat(ProblemParser.parse(List.of("123", "45", "6", "*")).solve()).isEqualTo(33210L);
    }

    @Test
    void shouldSliceBlockCorrectly() {
        assertThat(WorksheetSplitter.sliceBlock(getNormalizedExampleLines(), 0, 3)).containsExactly("123", "45", "6", "*");
    }

    private List<String> getNormalizedExampleLines() {
        return WorksheetNormalizer.padLines(worksheet.lines().toList());
    }
}