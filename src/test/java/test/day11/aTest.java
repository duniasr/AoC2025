package test.day11;

import software.ulpgc.aoc.day11.Reactor;
import software.ulpgc.aoc.day11.ReactorReader;
import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day11.a.PathCounting;

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
        Reactor network = ReactorReader.readFrom(List.of(input.split("\n")));
        PathCounting service = new PathCounting(network);
        // When
        long totalPaths = service.countPathsBetween("you", "out");
        // Then
        assertThat(totalPaths).isEqualTo(5L);
    }
}