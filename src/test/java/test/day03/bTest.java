package test.day03;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import software.ulpgc.aoc.day03.EmergencyPowerSystem;

class EmergencyPowerSystemTest {

    private static final String input = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
            """;

    @Test
    void should_calculate_maximum_joltage_using_twelve_batteries() {
        // Given
        EmergencyPowerSystem system = EmergencyPowerSystem.from(input);
        // When
        long totalOutput = system.calculateTotalOutputJoltage(12);
        // Then
        assertThat(totalOutput).isEqualTo(3121910778619L);
    }
}