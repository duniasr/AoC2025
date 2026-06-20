package test.day07;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day07.b.QuantumTachyonPhysics;
import software.ulpgc.aoc.day07.TachyonManifold;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class bTest {

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
    void shouldCountTimelinesInQuantumManifold() {
        // Given
        TachyonManifold manifold = TachyonManifold.fromDiagram(EXAMPLE_DIAGRAM);
        QuantumTachyonPhysics physics = new QuantumTachyonPhysics();

        // When
        long totalTimelines = manifold.simulate(physics).totalActiveTimelines();

        // Then
        assertThat(totalTimelines).isEqualTo(40L);
    }
}