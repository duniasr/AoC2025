package test.day11;

import software.ulpgc.aoc.day11.DeviceNetwork;
import software.ulpgc.aoc.day11.DeviceReader;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class aTest {

    @Test
    void should_find_5_paths_from_you_to_out() {
        // Given
        String input = """
            aaa: you hhh
            you: bbb ccc
            bbb: ddd eee
            ccc: ddd eee fff
            ddd: ggg
            eee: out
            fff: out
            ggg: out
            hhh: ccc fff iii
            iii: out
            """;
        DeviceNetwork network = DeviceReader.readFrom(List.of(input.split("\n")));
        // When
        long totalPaths = network.countPathsBetween("you", "out");
        // Then
        assertThat(totalPaths).isEqualTo(5L);
    }
}