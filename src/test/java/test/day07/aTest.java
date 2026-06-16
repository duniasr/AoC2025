package test.day07;

import software.ulpgc.aoc.day07.a.TachyonManifold;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class aTest {

    private final List<String> EXAMPLE_GRID = List.of(
            ".......S.......",
            "...............",
            ".......^.......",
            "...............",
            "......^.^......",
            "...............",
            ".....^.^.^.....",
            "...............",
            "....^.^...^....",
            "...............",
            "...^.^...^.^...",
            "...............",
            "..^...^.....^..",
            "...............",
            ".^.^.^.^.^...^.",
            "..............."
    );

    @Test
    void shouldCountSplitsInClassicalManifold() {
        assertThat(TachyonManifold.calculate(EXAMPLE_GRID)).isEqualTo(21L);
    }
}