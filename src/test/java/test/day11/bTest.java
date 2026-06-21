package test.day11;

import software.ulpgc.aoc.day11.DeviceNetwork;
import software.ulpgc.aoc.day11.DeviceReader;
import org.junit.jupiter.api.Test;
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
        DeviceNetwork network = DeviceReader.readFrom(List.of(input.split("\n")));
        // When
        long totalPaths = network.countPathsThrough("svr", "out", "dac", "fft");
        // Then
        assertThat(totalPaths).isEqualTo(2L);
    }
}
