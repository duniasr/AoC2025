package test.Day02;

import software.ulpgc.aoc.day02.common.RangeProcessor;
import software.ulpgc.aoc.day02.b.InvalidIdDetector;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class bTest {
    private final static String ranges = """
                                         11-22
                                         95-115
                                         998-1012
                                         1188511880-1188511890
                                         222220-222224
                                         1698522-1698528
                                         446443-446449
                                         38593856-38593862
                                         565653-565659
                                         824824821-824824827
                                         2121212118-2121212124""";

    @Test
    public void given_individual_ranges_should_sum_invalid_ids_with_part_b_rules() {
        assertEquals(33, RangeProcessor.sumInvalidIds("11-22", InvalidIdDetector::isInvalid));
        assertEquals(210, RangeProcessor.sumInvalidIds("95-115", InvalidIdDetector::isInvalid));
        assertEquals(2009, RangeProcessor.sumInvalidIds("998-1012", InvalidIdDetector::isInvalid));
        assertEquals(565656, RangeProcessor.sumInvalidIds("565653-565659", InvalidIdDetector::isInvalid));
    }

    @Test
    public void given_full_input_should_calculate_total_sum_for_part_b() {
        assertEquals(4174379265L, RangeProcessor.sumInvalidIds(ranges.replace("\n", ","), InvalidIdDetector::isInvalid));
    }
}