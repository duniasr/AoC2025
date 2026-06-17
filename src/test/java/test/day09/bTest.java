package test.day09;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day09.b.MovieTheater;

import static org.assertj.core.api.Assertions.assertThat;

class bTest {

    @Test
    void given_tiles_should_account_largest_valid_area() {
        String inputTiles = """    
                7,1
                11,1
                11,7
                9,7
                9,5
                2,5
                2,3
                7,3
                """;

        assertThat(MovieTheater.from(inputTiles).findLargestValidRectangleArea()).isEqualTo(24L);
    }
}
