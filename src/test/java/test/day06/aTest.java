package test.day06;

import org.junit.jupiter.api.DisplayName;
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
        // Given & When
        long grandTotal = HorizontalCephalopodWorksheet.from(exampleWorksheet)
                .unroll()
                .calculateGrandTotal();

        // Then
        // 33210 + 490 + 4243455 + 401 = 4277556
        assertThat(grandTotal).isEqualTo(4277556L);
    }
}