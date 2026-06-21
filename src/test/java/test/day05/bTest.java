package test.day05;

import software.ulpgc.aoc.day05.b.InventoryDatabase;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class bTest {
    private final static String input = """
                                        3-5
                                        10-14
                                        16-20
                                        12-18
                                        
                                        1
                                        5
                                        8
                                        11
                                        17
                                        32""";

    @Test
    public void given_inventory_database_should_calculate_14_total_capacity() {
        // Given
        InventoryDatabase database = InventoryDatabase.from(input);
        // When
        long totalCapacity = database.calculateTotalFreshCapacity();
        // Then
        assertThat(totalCapacity).isEqualTo(14L);
    }
}