package test.day12;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day12.PresentsFit;
import software.ulpgc.aoc.day12.PuzzleReader;
import software.ulpgc.aoc.day12.TreeRegion;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class aTest {

    private static final String christmasTree = """
            0:
            ###
            ##.
            ##.
            
            1:
            ###
            ##.
            .##
            
            2:
            .##
            ###
            ##.
            
            3:
            ##.
            ###
            ##.
            
            4:
            ###
            #..
            ###
            
            5:
            ###
            .#.
            ###
            
            4x4: 0 0 0 0 2 0
            12x5: 1 0 1 0 2 2
            12x5: 1 0 1 0 3 2""";

    @Test
    public void given_presents_and_trees_should_count_fitting_trees() {
        // Given
        List<TreeRegion> regions = PuzzleReader.readFrom(christmasTree);
        PresentsFit presents = PresentsFit.fitLogic();
        // When
        long fittingTrees = regions.stream().filter(presents::fitUnderRegion).count();
        // Then
        assertThat(fittingTrees).isEqualTo(2);
    }
}