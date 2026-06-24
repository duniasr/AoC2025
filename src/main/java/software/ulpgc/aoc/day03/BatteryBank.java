package software.ulpgc.aoc.day03;

import java.util.stream.IntStream;

public class BatteryBank {

    private final String sequence;

    private BatteryBank(String sequence) {
        this.sequence = sequence;
    }

    public static BatteryBank from(String sequence) {
        return new BatteryBank(sequence);
    }

    public long calculateMaxJoltage(int targetBatteries) {
        return Long.parseLong(recursiveExtract(targetBatteries, 0, ""));
    }

    // Base case: returns the accumulator (acc) when no batteries are left (left == 0).
    private String recursiveExtract(int left, int start, String acc) {
        return left == 0 ? acc : continueExtraction(left, start, acc);
    }

    // Recursive step: finds the best digit, appends it to the accumulator, and shifts the search window.
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