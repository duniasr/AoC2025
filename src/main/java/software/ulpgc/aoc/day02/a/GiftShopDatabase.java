package software.ulpgc.aoc.day02.a;

import software.ulpgc.aoc.day02.Range;
import java.util.Arrays;
import java.util.List;

public class GiftShopDatabase {
    public static List<Range> from(String rawRanges) {
        return Arrays.stream(rawRanges.split(","))
                .map(Range::from)
                .toList();
    }

    public static long sumInvalidIds(List<Range> ranges) {
        return ranges.stream()
                .flatMapToLong(Range::stream)
                .filter(GiftShopDatabase::isTwiceRepeatedPattern)
                .sum();
    }

    private static boolean isTwiceRepeatedPattern(long id) {
        String text = String.valueOf(id);
        return text.length() % 2 == 0 &&
                text.substring(0, text.length() / 2).equals(text.substring(text.length() / 2));
    }
}