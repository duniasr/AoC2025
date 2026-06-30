package software.ulpgc.aoc.day04.b;

import software.ulpgc.aoc.day04.Coordinate;
import java.util.List;

public class Forklift {

    public long removeAllAccessibleRolls(PaperRollDiagram diagram) {
        return recursiveRemove(diagram, 0);
    }

    private long recursiveRemove(PaperRollDiagram diagram, long accumulatedTotal) {
        List<Coordinate> toRemove = findAccessibleRolls(diagram);

        // Base case: No more accessible rolls, terminate recursion
        if (toRemove.isEmpty()) return accumulatedTotal;

        toRemove.forEach(diagram::removeRoll);
        
        // Recursive case: Continue cleaning passing the accumulated state
        return recursiveRemove(diagram, accumulatedTotal + toRemove.size());
    }

    private List<Coordinate> findAccessibleRolls(PaperRollDiagram diagram) {
        return diagram.streamAllCoordinates()
                .filter(diagram::isRoll)
                .filter(coord -> this.canAccess(coord, diagram))
                .toList();
    }

    private boolean canAccess(Coordinate coord, PaperRollDiagram diagram) {
        return coord.streamAdjacentCoordinates()
                .filter(diagram::isWithinBounds)
                .filter(diagram::isRoll)
                .count() < 4;
    }
}
