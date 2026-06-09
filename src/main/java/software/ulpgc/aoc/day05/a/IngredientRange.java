package software.ulpgc.aoc.day05.a;

public record IngredientRange(long min, long max) {
    public static IngredientRange parse(String line) {
        String[] bounds = line.split("-");
        return new IngredientRange(Long.parseLong(bounds[0]), Long.parseLong(bounds[1]));
    }
    // Comprueba si un ID específico cae dentro de este rango.
    public boolean contains(long id) {
        return id >= min && id <= max;
    }
}