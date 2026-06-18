package test.day06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day06.b.VerticalCephalopodWorksheet;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class bTest {

    private final List<String> exampleWorksheet = List.of(
            "123 328  51 64 ",
            " 45 64  387 23 ",
            "  6 98  215 314",
            "*   +   *   +  "
    );

    @Test
    void should_calculate_grand_total_vertically() {
        // Given & When
        long grandTotal = VerticalCephalopodWorksheet.from(exampleWorksheet)
                .unroll()
                .calculateGrandTotal();

        // Then
        // 1058 + 3253600 + 625 + 8544 = 3263827
        assertThat(grandTotal).isEqualTo(3263827L);
    }
}