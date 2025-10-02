package ru.volgatest.brake.InsideWindows.IntermittentBraking.Cycle;

import ru.volgatest.brake.InsideWindows.IntermittentBraking.SubCycle.SubCycleModel;

import java.util.*;

public class CycleModel {
    public String name;
    public int repeat;
    public List<SubCycleModel> subCycleModels = new ArrayList<>();
    public Map<UUID, SubCycleModel> subCycleMap = new HashMap<>();

    public CycleModel clone() {
        try {
            return (CycleModel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed");
        }
    }
}
