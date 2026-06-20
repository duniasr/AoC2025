package test.day04;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day04.a.PaperRollDiagram;

import static org.assertj.core.api.Assertions.assertThat;
public class aTest {

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
    public void given_paper_grid_should_count_13_accessible_rolls() {
        // Given
        PaperRollDiagram diagram = PaperRollDiagram.from(input);
        // When
        long accessibleRolls = diagram.countAccessibleRolls();
        // Then
        assertThat(accessibleRolls).isEqualTo(13L);
    }
}