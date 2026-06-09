package test.java.test.Day01;

import main.java.software.ulpgc.aoc.day01.a.SafeDecoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class aTest {

    // El bloque con las órdenes mágicas que nos dieron los Elfos en el mapa de ejemplo.
    private static final String ORDERS = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """;

    // Comprueba que las instrucciones del bloque abren la caja fuerte correctamente.
    @Test
    public void should_decode_password_correctly_for_specification_example() {
        assertEquals(3, SafeDecoder.decodePassword(ORDERS.lines().filter(line -> !line.isBlank())));
    }
}