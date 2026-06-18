package test.day07;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day07.a.ClassicalTachyonPhysics;
import software.ulpgc.aoc.day07.common.TachyonManifold;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class aTest {

    private static final List<String> EXAMPLE_DIAGRAM = List.of(
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
        // Given
        TachyonManifold manifold = TachyonManifold.fromDiagram(EXAMPLE_DIAGRAM);
        ClassicalTachyonPhysics physics = new ClassicalTachyonPhysics();

        // When
        long totalSplits = manifold.simulate(physics).totalSplits();

        // Then
        assertThat(totalSplits).isEqualTo(21L);
    }
}