package test.Day06;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day06.b.CephalopodDecoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class bTest {
        private static final List<String> COMPLEX_WORKSHEET = List.of(
            "123 328  51 64 ",
            " 45 64  387 23 ",
            "  6 98  215 314",
            "* +   * +  "
    );

    @Test
    void shouldSolveComplexWorksheet() {
        assertThat(CephalopodDecoder.decode(COMPLEX_WORKSHEET)).isEqualTo(4277556L);
    }

    @Test
    void shouldSolveFullPuzzleInput() throws Exception {
        // Leemos el archivo y pasamos las líneas directamente a tu decoder (cero variables)
        assertThat(CephalopodDecoder.decode(readPuzzleInput())).isEqualTo(4405895212738L);
    }

    // Encapsulamos la lectura del archivo usando la ruta que tenías en tu Main
    private List<String> readPuzzleInput() throws Exception {
        return Files.readAllLines(Path.of("src/main/java/software/ulpgc/resources/day06/input.txt"));
    }
}