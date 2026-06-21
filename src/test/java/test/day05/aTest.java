package test.day05;

import software.ulpgc.aoc.day05.a.InventoryDatabase;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class aTest {
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
    public void given_inventory_database_should_count_3_fresh_ingredients() {
        // Given
        InventoryDatabase database = InventoryDatabase.from(input);
        // When
        long freshCount = database.countFreshAvailableIngredients();
        // Then
        assertThat(freshCount).isEqualTo(3L);
    }
}
