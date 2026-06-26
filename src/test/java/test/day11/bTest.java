package test.day11;

import software.ulpgc.aoc.day11.Reactor;
import software.ulpgc.aoc.day11.ReactorReader;
import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day11.b.PathCounting;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class bTest {

    @Test
    void should_find_2_paths_visiting_dac_and_fft() {
        // Given
        String input = """
            svr: aaa bbb
            aaa: fft
            fft: ccc
            bbb: tty
            tty: ccc
            ccc: ddd eee
            ddd: hub
            hub: fff
            eee: dac
            dac: fff
            fff: ggg hhh
            ggg: out
            hhh: out
            """;
        Reactor network = ReactorReader.readFrom(List.of(input.split("\n")));
        PathCounting service = new PathCounting(network);
        // When
        long totalPaths = service.countPathsThrough("svr", "out", "dac", "fft");
        // Then
        assertThat(totalPaths).isEqualTo(2L);
    }
}
