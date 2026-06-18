package software.ulpgc.aoc.day07.common;

import java.util.List;

public class TachyonManifold {
    private final List<String> diagram;

    private TachyonManifold(List<String> diagram) {
        this.diagram = diagram;
    }

    public static TachyonManifold fromDiagram(List<String> diagram) {
        return new TachyonManifold(diagram);
    }

    public <T> T simulate(TachyonPhysics<T> physics) {
        T initialState = physics.initialize(diagram.getFirst());
        return diagram.stream().reduce(initialState, physics::propagateThrough, (a, b) -> a);
    }
}