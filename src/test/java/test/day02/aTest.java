package test.day02;

import software.ulpgc.aoc.day02.Range;
import software.ulpgc.aoc.day02.a.GiftShopDatabase;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class aTest {
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
    public void given_individual_ranges_should_sum_invalid_ids_with_part_a_rules() {
        // Given
        String range = "1698522-1698528";
        var database = GiftShopDatabase.from(range);
        // When
        long result = GiftShopDatabase.sumInvalidIds(database);
        // Then
        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void given_full_input_should_calculate_total_sum_for_part_a() {
        // Given
        String input = ranges.replace("\n", ",");
        List<Range> database = GiftShopDatabase.from(input);
        // When
        long result = GiftShopDatabase.sumInvalidIds(database);
        // Then
        assertThat(result).isEqualTo(1227775554L);
    }
}