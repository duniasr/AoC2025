package test.day02;

import software.ulpgc.aoc.day02.Range;
import software.ulpgc.aoc.day02.b.GiftShopDatabase;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void given_individual_ranges_should_sum_invalid_ids() {
        // Given
        String range = "1210-1215";
        List<Range> database = GiftShopDatabase.from(range);
        // When
        long result = GiftShopDatabase.sumInvalidIds(database);
        // Then
        assertThat(result).isEqualTo(1212L);
    }

    @Test
    public void given_full_input_should_calculate_total_sum_for() {
        // Given
        String input = ranges.replace("\n", ",");
        List<Range> database = GiftShopDatabase.from(input);
        // When
        long result = GiftShopDatabase.sumInvalidIds(database);
        // Then
        assertThat(result).isGreaterThan(0L);
    }
}