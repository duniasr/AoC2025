package test.day01;

import software.ulpgc.aoc.day01.b.SafeDecoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class bTest {

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

    @Test
    public void should_decode_part_b_password_correctly() {
        assertEquals(6, SafeDecoder.decodePassword(ORDERS.lines().filter(line -> !line.isBlank())));
    }
}