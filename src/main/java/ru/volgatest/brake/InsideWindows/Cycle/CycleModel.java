package ru.volgatest.brake.InsideWindows.Cycle;

import ru.volgatest.brake.InsideWindows.SubCycle.SubCycleModel;

import java.util.ArrayList;
import java.util.List;

public class CycleModel {
    public String name;
    public int repeat;
    public List<SubCycleModel> subCycleModels = new ArrayList<>();

    public CycleModel clone() {
        CycleModel cycleModel = new CycleModel();
        cycleModel.name = this.name;
        cycleModel.repeat = this.repeat;
        cycleModel.subCycleModels.addAll(this.subCycleModels);
        return cycleModel;
    }
}
