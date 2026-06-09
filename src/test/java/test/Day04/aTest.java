package test.Day04;

import software.ulpgc.aoc.day04.a.PaperGrid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(13, PaperGrid.countAccessibleRolls(input));
    }
}