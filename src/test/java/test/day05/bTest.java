package test.day05;

import software.ulpgc.aoc.day05.b.InventoryAnalyzer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class bTest {

    // Usamos el mismo input, demostrando que nuestro código de la Parte B
    // es capaz de extraer solo el primer bloque y desechar el resto.
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
        // En la Parte B, fusionamos los rangos solapados y sumamos su capacidad total.
        assertEquals(14, InventoryAnalyzer.countTotalFreshCapacity(input));
    }
}