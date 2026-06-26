package test.day10;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import software.ulpgc.aoc.day10.b.JoltageConfigurator;
import software.ulpgc.aoc.day10.MachineCommand;
import software.ulpgc.aoc.day10.MachineManual;

class bTest {

    @Test
    void should_configure_joltage_levels_in_33_presses_total() {
        // Given
        String input = """
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
            """;
        MachineCommand command = new JoltageConfigurator();
        // When
        long totalPresses = input.lines()
                .filter(line -> !line.isBlank())
                .map(MachineManual::readMachineFrom)
                .mapToLong(command::execute)
                .sum();
        // Then
        assertThat(totalPresses).isEqualTo(33L);
    }
}