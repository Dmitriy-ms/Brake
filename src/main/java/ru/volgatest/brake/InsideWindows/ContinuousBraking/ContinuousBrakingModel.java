package ru.volgatest.brake.InsideWindows.ContinuousBraking;

public class ContinuousBrakingModel {
    public String name;
    public int tMin = 0;
    public int tMax = 0;
    public float slope = 0;          // Уклон
    public float distance = 0;       // Расстояние
    public float speed = 0;          // Скорость
    public float airFlowRate = 0; // Скорость потока воздуха

    public ContinuousBrakingModel clone() {
        return this;
    }
}
