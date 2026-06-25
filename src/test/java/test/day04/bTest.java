package test.day04;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day04.b.Forklift;
import software.ulpgc.aoc.day04.b.PaperRollDiagram;

import static org.assertj.core.api.Assertions.assertThat;

public class bTest {

    private final static String input = """
                                        ..@@.@@@@.
                                        @@@.@.@.@@
                                        @@@@@.@.@@
                                        @.@@@@..@.
                                        @@.@@@@.@@
                                        .@@@@@@@.@
                                        .@.@.@.@@@
                                        @.@@@.@@@@
                                        .@@@@@@@@.
                                        @.@.@@@.@.""";

    @Test
    public void given_paper_grid_should_simulate_and_count_43_total_removals() {
        // Given
        PaperRollDiagram diagram = PaperRollDiagram.from(input);
        Forklift forklift = new Forklift();
        // When
        long totalRemoved = forklift.removeAllAccessibleRolls(diagram);
        // Then
        assertThat(totalRemoved).isEqualTo(43L);
    }
}