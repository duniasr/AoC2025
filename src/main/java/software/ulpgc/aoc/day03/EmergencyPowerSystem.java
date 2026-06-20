package software.ulpgc.aoc.day03;

import java.util.Arrays;
import java.util.List;

public class EmergencyPowerSystem {

    private final List<BatteryBank> banks;

    private EmergencyPowerSystem(List<BatteryBank> banks) {
        this.banks = banks;
    }

    public static EmergencyPowerSystem from(String rawNotes) {
        return new EmergencyPowerSystem(parseBanks(rawNotes));
    }

    private static List<BatteryBank> parseBanks(String notes) {
        return Arrays.stream(notes.split("\\R"))
                .map(String::trim)
                .filter(s -> !s.isEmpty()).map(BatteryBank::new)
                .toList();
    }

    public long calculateTotalOutputJoltage(int targetBatteries) {
        return banks.stream()
                .mapToLong(bank -> bank.calculateMaxJoltage(targetBatteries))
                .sum();
    }
}