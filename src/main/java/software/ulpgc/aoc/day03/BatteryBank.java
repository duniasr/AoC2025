package software.ulpgc.aoc.day03;

import java.util.stream.IntStream;

public class BatteryBank {

    private final String sequence;

    public BatteryBank(String sequence) {
        this.sequence = sequence;
    }

    public long calculateMaxJoltage(int targetBatteries) {
        return Long.parseLong(recursiveExtract(targetBatteries, 0, ""));
    }

    private String recursiveExtract(int left, int start, String acc) {
        return left == 0 ? acc : continueExtraction(left, start, acc);
    }

    private String continueExtraction(int left, int start, String acc) {
        int bestIdx = findMaxDigitIndex(start, sequence.length() - left);
        return recursiveExtract(left - 1, bestIdx + 1, acc + sequence.charAt(bestIdx));
    }

    private int findMaxDigitIndex(int start, int end) {
        return IntStream.rangeClosed(start, end).reduce(start, this::keepMaxIndex);
    }

    private int keepMaxIndex(int best, int current) {
        return sequence.charAt(current) > sequence.charAt(best) ? current : best;
    }
}