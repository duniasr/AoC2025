package test.day08;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day08.b.Playground;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import software.ulpgc.aoc.day08.JunctionBox;
import software.ulpgc.aoc.day08.UnionFindElectricalGrid;
import java.util.stream.IntStream;

public class bTest {
    // Given
    private final List<String> Input = List.of(
            "162,817,812", "57,618,57", "906,360,560", "592,479,940",
            "352,342,300", "466,668,158", "542,29,236", "431,825,988",
            "739,650,466", "52,470,668", "216,146,977", "819,987,18",
            "117,168,530", "805,96,715", "346,949,466", "970,615,88",
            "941,993,340", "862,61,35", "984,92,344", "425,690,689"
    );

    @Test
    public void should_return_14136_as_product_of_coordinates_of_the_last_installed_string() {
        // When
        List<JunctionBox> boxes = IntStream.range(0, Input.size())
                .mapToObj(i -> JunctionBox.parse(i, Input.get(i))).toList();
        long result = new Playground(boxes, new UnionFindElectricalGrid(boxes.size()))
                .connectUntilSingleCircuit();
        // Then
        assertThat(result).isEqualTo(25272L);
    }
}