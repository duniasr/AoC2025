package software.ulpgc.aoc.day02.common;

import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class RangeProcessor {

    public static long sumInvalidIds(String fullInputLine, LongPredicate validatorRule) {
        return Arrays.stream(fullInputLine.split(","))
                .mapToLong(range -> processSingleRange(range, validatorRule))
                .sum();
    }

    private static long processSingleRange(String rangeText, LongPredicate validatorRule) {
        return extractBoundaries(rangeText.split("-"))
                .filter(validatorRule)
                .sum();
    }

    private static LongStream extractBoundaries(String[] bounds) {
        return LongStream.rangeClosed(Long.parseLong(bounds[0].trim()), Long.parseLong(bounds[1].trim()));
    }
}