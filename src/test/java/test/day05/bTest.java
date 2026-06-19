package test.day05;

import software.ulpgc.aoc.day05.b.InventoryDatabase;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(14, totalCapacity, "La Parte B debería devolver una capacidad total de 14 tras fusionar rangos.");
    }
}