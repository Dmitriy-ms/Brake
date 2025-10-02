package ru.volgatest.brake.InsideWindows.SubCycle;

import java.util.UUID;

public class SubCycleModel {

    public UUID uuid = UUID.randomUUID();

    public String name = "";

    boolean isTemperatureCheckedEachCycle = false;
    boolean isPreheatingEnabled = false;
    boolean isPrecoolingEnabled = false;

    public int tMin = 0;
    public int tMax = 0;

    double initialSpeed = 0;      // начальная скорость, км/ч
    double finalSpeed = 0;        // конечная скорость, км/ч
    double airFlowSpeed = 0;      // скорость потока воздуха, м/с
    int cycleDuration = 0;        // длительность цикла, сек (если только целые значения)
    int repeat = 0;           // количество циклов

    public SubCycleModel clone() {
        SubCycleModel subCycleModel = new SubCycleModel();
        subCycleModel.uuid = new UUID(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
        subCycleModel.name = this.name;
        subCycleModel.isTemperatureCheckedEachCycle = this.isTemperatureCheckedEachCycle;
        subCycleModel.isPreheatingEnabled = this.isPreheatingEnabled;
        subCycleModel.isPrecoolingEnabled = this.isPrecoolingEnabled;
        subCycleModel.tMin = this.tMin;
        subCycleModel.tMax = this.tMax;
        subCycleModel.initialSpeed = this.initialSpeed;
        subCycleModel.finalSpeed = this.finalSpeed;
        subCycleModel.airFlowSpeed = this.airFlowSpeed;
        subCycleModel.cycleDuration = this.cycleDuration;
        subCycleModel.repeat = this.repeat;
        return subCycleModel;
    }
}
