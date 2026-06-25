package software.ulpgc.aoc.day04.a;

import software.ulpgc.aoc.day04.Coordinate;

public class Forklift {

    public long countAccessibleRolls(PaperRollDiagram diagram) {
        return diagram.streamAllCoordinates()
                .filter(diagram::isRoll)
                .filter(coord -> this.canAccess(coord, diagram))
                .count();
    }

    private boolean canAccess(Coordinate coord, PaperRollDiagram diagram) {
        return coord.streamAdjacentCoordinates()
                .filter(diagram::isWithinBounds)
                .filter(diagram::isRoll)
                .count() < 4;
    }
}
