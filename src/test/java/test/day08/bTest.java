package test.day08;

import org.junit.jupiter.api.Test;
import software.ulpgc.aoc.day08.Playground;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class bTest {

    private final List<String> Input = List.of(
            "162,817,812", "57,618,57", "906,360,560", "592,479,940",
            "352,342,300", "466,668,158", "542,29,236", "431,825,988",
            "739,650,466", "52,470,668", "216,146,977", "819,987,18",
            "117,168,530", "805,96,715", "346,949,466", "970,615,88",
            "941,993,340", "862,61,35", "984,92,344", "425,690,689"
    );

    @Test
    public void should_return_25272_as_product_of_X_coordinates_for_the_final_circuit_connection() {
        long result = Playground.fromLines(Input)
                .connectUntilSingleCircuit();
        assertThat(result)
                .as("Producto de las coordenadas X de las dos últimas cajas unidas para cerrar la red")
                .isEqualTo(25272L);
    }
}