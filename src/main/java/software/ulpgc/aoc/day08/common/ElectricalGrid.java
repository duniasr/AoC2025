package software.ulpgc.aoc.day08.common;

import java.util.List;

public interface ElectricalGrid {
    boolean tryConnecting(JunctionBox a, JunctionBox b);
    List<Integer> getCircuitSizes();
}