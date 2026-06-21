package test.day09;

import software.ulpgc.aoc.day09.a.MovieTheater;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class aTest {

    @Test
    void given_tiles_should_account_largest_area() {
        // Given
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
        // When
        long largestArea = MovieTheater.from(inputTiles).findLargestRectangleArea();
        // Then
        assertThat(largestArea).isEqualTo(50L);
    }
}
