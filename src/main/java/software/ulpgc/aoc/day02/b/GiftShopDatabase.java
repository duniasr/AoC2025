package software.ulpgc.aoc.day02.b;

import software.ulpgc.aoc.day02.Range;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class GiftShopDatabase {

    private GiftShopDatabase() {}

    private static final Pattern SILLY_PATTERN = Pattern.compile("^(.+)\\1+$");

    public static List<Range> from(String rawRanges) {
        return Arrays.stream(rawRanges.split(","))
                .map(Range::from)
                .toList();
    }

    public static long sumInvalidIds(List<Range> ranges) {
        return ranges.stream()
                .flatMapToLong(Range::expandToSequence)
                .filter(GiftShopDatabase::isAnyRepeatedPattern)
                .sum();
    }

    private static boolean isAnyRepeatedPattern(long id) {
        return SILLY_PATTERN.matcher(String.valueOf(id)).matches();
    }
}