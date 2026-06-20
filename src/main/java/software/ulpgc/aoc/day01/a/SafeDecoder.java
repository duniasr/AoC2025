package software.ulpgc.aoc.day01.a;

import software.ulpgc.aoc.day01.Rotation;
import java.util.stream.Stream;

public class SafeDecoder {
    public static int decodePassword(Stream<String> document) {
        return document.map(Rotation::from)
                .reduce(Dial.initial(), Dial::rotate, (d1, d2) -> d1)
                .passwordScore();
    }
}