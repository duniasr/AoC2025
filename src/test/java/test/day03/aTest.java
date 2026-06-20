package test.day03;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import software.ulpgc.aoc.day03.EmergencyPowerSystem;

class aTest {

    private static final String input = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
            """;

    @Test
    void should_calculate_maximum_joltage_using_two_batteries() {
        // Given
        EmergencyPowerSystem system = EmergencyPowerSystem.from(input);
        // When
        long totalOutput = system.calculateTotalOutputJoltage(2);
        // Then, 357 (98 + 89 + 78 + 92)
        assertThat(totalOutput).isEqualTo(357L);
    }
}