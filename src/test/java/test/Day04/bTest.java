package test.Day04;

import software.ulpgc.aoc.day04.b.PaperGrid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(43, PaperGrid.countTotalRemovals(input));
    }
}