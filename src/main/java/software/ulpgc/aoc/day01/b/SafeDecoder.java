package software.ulpgc.aoc.day01.b;

import main.java.software.ulpgc.aoc.day01.common.Rotation;
import java.util.stream.Stream;

public class SafeDecoder {

    public static int decodePassword(Stream<String> lines) {
        return lines.map(Rotation::fromText)
                .reduce(new DialState(50, 0), DialState::applyRotation, (s1, s2) -> s1)
                .zeroCount();
    }
}