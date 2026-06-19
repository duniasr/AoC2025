package software.ulpgc.aoc.day05.a;

public record FreshIdRange(long min, long max) {

    public static FreshIdRange from(String line) {
        String[] bounds = line.split("-");
        return new FreshIdRange(Long.parseLong(bounds[0]), Long.parseLong(bounds[1]));
    }

    // Determines if a specific ingredient ID is considered fresh within this range
    public boolean covers(long id) {
        return id >= min && id <= max;
    }
}