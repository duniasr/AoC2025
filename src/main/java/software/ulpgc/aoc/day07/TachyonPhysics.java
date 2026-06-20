package software.ulpgc.aoc.day07;

public interface TachyonPhysics<T> {

    // Locates the 'S' and prepares the initial timeline/beam state
    T initialize(String firstRow);

    // Calculates the next state of the beams after moving one step downward
    T propagateThrough(T state, String row);
}