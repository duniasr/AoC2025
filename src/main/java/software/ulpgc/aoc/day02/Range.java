package software.ulpgc.aoc.day02;

import java.util.stream.LongStream;

public record Range(long start, long end) {

    public static Range from(String text) {
        String[] parts = text.split("-");
        return new Range(Long.parseLong(parts[0].trim()), Long.parseLong(parts[1].trim()));
    }

    public LongStream expandToSequence() {
        return LongStream.rangeClosed(start, end);
    }
}