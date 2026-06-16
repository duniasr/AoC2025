package test.day03;

import software.ulpgc.aoc.day03.common.PowerGrid;
import software.ulpgc.aoc.day03.b.BatteryBank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class bTest {
    private final static String input = """
                                        987654321111111
                                        811111111111119
                                        234234234234278
                                        818181911112111""";

    @Test
    public void given_individual_banks_should_find_max_12_digit_joltage() {
        assertEquals(987654321111L, BatteryBank.maxJoltage("987654321111111"));
        assertEquals(811111111119L, BatteryBank.maxJoltage("811111111111119"));
        assertEquals(434234234278L, BatteryBank.maxJoltage("234234234234278"));
        assertEquals(888911112111L, BatteryBank.maxJoltage("818181911112111"));
    }

    @Test
    public void given_full_input_should_calculate_total_massive_joltage() {
        assertEquals(3121910778619L, PowerGrid.calculateTotalJoltage(input, BatteryBank::maxJoltage));
    }
}