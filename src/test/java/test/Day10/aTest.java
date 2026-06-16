package test.Day10;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day10.a.Machine;
import software.ulpgc.aoc.day10.a.Parser;

import static org.assertj.core.api.Assertions.assertThat;

class aTest {

    @Test
    void should_solve_all_machines_together_in_7_presses() {
        String input = """
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
            """;

        int totalPresses = input.lines()
                .filter(line -> !line.isBlank())
                .map(Parser::parse)
                .mapToInt(Machine::solve)
                .sum();

        assertThat(totalPresses).isEqualTo(7);
    }
}