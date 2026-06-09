package test.Day05;

import software.ulpgc.aoc.day05.a.InventoryAnalyzer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class aTest {
    // El input exacto del enunciado, con su línea en blanco separadora
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
        // En la Parte A, buscamos cuántos de los IDs de abajo (1, 5, 8...) son válidos.
        assertEquals(3, InventoryAnalyzer.countFreshIngredients(input));
    }
}
