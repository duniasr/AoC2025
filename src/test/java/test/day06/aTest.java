package test.day06;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day06.a.HorizontalCephalopodWorksheet;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class aTest {

    private final List<String> exampleWorksheet = List.of(
            "123 328  51 64 ",
            " 45 64  387 23 ",
            "  6 98  215 314",
            "*   +   *   +  "
    );
    @Test
    void should_calculate_grand_total_horizontally() {
        // Given
        HorizontalCephalopodWorksheet worksheet = (HorizontalCephalopodWorksheet) HorizontalCephalopodWorksheet.from(exampleWorksheet);
        // When
        long grandTotal = worksheet
                .unroll()
                .calculateGrandTotal();
        // Then
        assertThat(grandTotal).isEqualTo(4277556L);
    }
}