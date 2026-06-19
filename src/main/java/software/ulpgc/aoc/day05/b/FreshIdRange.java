package software.ulpgc.aoc.day05.b;

public record FreshIdRange(long min, long max) implements Comparable<FreshIdRange> {

    public static FreshIdRange from(String line) {
        String[] bounds = line.split("-");
        return new FreshIdRange(Long.parseLong(bounds[0]), Long.parseLong(bounds[1]));
    }
    public long size() {
        return max - min + 1;
    }

    public boolean canMergeWith(FreshIdRange other) {
        return this.max >= other.min - 1;
    }

    public FreshIdRange mergeWith(FreshIdRange other) {
        return new FreshIdRange(Math.min(this.min, other.min), Math.max(this.max, other.max));
    }
    @Override
    public int compareTo(FreshIdRange other) {
        return Long.compare(this.min, other.min);
    }
}