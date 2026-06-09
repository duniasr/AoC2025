package test.java.test.Day03;
import main.java.software.ulpgc.aoc.day03.common.PowerGrid;
import main.java.software.ulpgc.aoc.day03.a.BatteryBank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class aTest {
    private final static String input = """
                                        987654321111111
                                        811111111111119
                                        234234234234278
                                        818181911112111""";

    @Test
    public void given_individual_banks_should_find_max_joltage() {
        assertEquals(98, BatteryBank.maxJoltage("987654321111111"));
        assertEquals(89, BatteryBank.maxJoltage("811111111111119"));
        assertEquals(78, BatteryBank.maxJoltage("234234234234278"));
        assertEquals(92, BatteryBank.maxJoltage("818181911112111"));
    }

    @Test
    public void given_full_input_should_calculate_total_joltage() {
        assertEquals(357, PowerGrid.calculateTotalJoltage(input, BatteryBank::maxJoltage));
    }
}