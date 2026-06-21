package test.day06;

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
        // Given
        VerticalCephalopodWorksheet worksheet = (VerticalCephalopodWorksheet) VerticalCephalopodWorksheet.from(exampleWorksheet);
        // When
        long grandTotal = worksheet
                .unroll()
                .calculateGrandTotal();
        // Then
        assertThat(grandTotal).isEqualTo(3263827L);
    }
}