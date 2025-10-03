package ru.volgatest.brake.InsideWindows.IntermittentBraking.Cycle;

import ru.volgatest.brake.InsideWindows.IntermittentBraking.SubCycle.SubCycleModel;

import java.util.*;

public class CycleModel {
    public String name;
    public int repeat;
//    public List<SubCycleModel> subCycleModels = new ArrayList<>();
    public Map<UUID, Integer> subCycleMap = new LinkedHashMap<>();

    //Возмоно надо будет сделать List<Map<UUID, Integer>> для того чтобы хранить несколько повторений одно и того же подцикла в одном цикле

    public CycleModel clone() {
        try {
            return (CycleModel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed");
        }
    }
}
