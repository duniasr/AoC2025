package software.ulpgc.aoc.day05.b;

public record IngredientRange(long min, long max) implements Comparable<IngredientRange> {
    public static IngredientRange parse(String line) {
        String[] bounds = line.split("-");
        return new IngredientRange(Long.parseLong(bounds[0]), Long.parseLong(bounds[1]));
    }
    // Calcula cuántos números exactos hay dentro de este rango.
    public long size() {
        return max - min + 1;
    }
    // Dos rangos se pueden fusionar si se solapan o si son continuos (ej. 1-3 y 4-6).
    public boolean canMergeWith(IngredientRange other) {
        return this.max >= other.min - 1;
    }
    // Crea un nuevo "Súper Rango" que abarca desde el inicio del primero hasta el final más lejano.
    public IngredientRange mergeWith(IngredientRange other) {
        return new IngredientRange(Math.min(this.min, other.min), Math.max(this.max, other.max));
    }
    // Le enseña a Java a ordenar estos objetos basándose en su valor 'min'.
    @Override
    public int compareTo(IngredientRange other) {
        return Long.compare(this.min, other.min);
    }
}