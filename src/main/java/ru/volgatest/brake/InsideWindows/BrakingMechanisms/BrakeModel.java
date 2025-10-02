package ru.volgatest.brake.InsideWindows.BrakingMechanisms;

public class BrakeModel {


//    Радиус качения колеса	float
//    Минимальная температура объекта испытания	float
//    Максимальная температура объекта испытания	float
//    Максимальное усилие торможения	float
//    Максимальное усилие статического нагружения	float
//    Минимальное давление воздуха	float
//    Минимальная температура воздуха	float
//    Максимальная температура воздуха	float

    float rollingRadius = 0;
    float minTemperature = 0;
    float maxTemperature = 0;
    float maxBrakingForce = 0;
    float maxStaticLoad = 0;
    float minAirPressure = 0;
    float minAirTemperature = 0;
    float maxAirTemperature = 0;


    String name;
//    String manufacturer;
//    String brand;
//    String type;
//    String additionalInformation;
//    double weightWheel;
//    double rollingRadius;

    public BrakeModel() {
    }

    //    public BrakeModel clone() {
//        BrakeModel brake = new BrakeModel();
//        brake.name = this.name;
//        brake.manufacturer = this.manufacturer;
//        brake.brand = this.brand;
//        brake.type = this.type;
//        brake.additionalInformation = this.additionalInformation;
//        brake.weightWheel = this.weightWheel;
//        brake.rollingRadius = this.rollingRadius;
//        return brake;
//    }
//
    public BrakeModel clone() {
        BrakeModel brake = new BrakeModel();
        brake.name = this.name;
        brake.rollingRadius = this.rollingRadius;
        brake.minTemperature = this.minTemperature;
        brake.maxTemperature = this.maxTemperature;
        brake.maxBrakingForce = this.maxBrakingForce;
        brake.maxStaticLoad = this.maxStaticLoad;
        brake.minAirPressure = this.minAirPressure;
        brake.minAirTemperature = this.minAirTemperature;
        brake.maxAirTemperature = this.maxAirTemperature;
        return brake;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public String getManufacturer() {
//        return manufacturer;
//    }
//
//    public void setManufacturer(String manufacturer) {
//        this.manufacturer = manufacturer;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getAdditionalInformation() {
//        return additionalInformation;
//    }
//
//    public void setAdditionalInformation(String additionalInformation) {
//        this.additionalInformation = additionalInformation;
//    }
//
//    public double getWeightWheel() {
//        return weightWheel;
//    }
//
//    public void setWeightWheel(double weightWheel) {
//        this.weightWheel = weightWheel;
//    }

    public double getRollingRadius() {
        return rollingRadius;
    }

    public void setRollingRadius(float rollingRadius) {
        this.rollingRadius = rollingRadius;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMaxBrakingForce() {
        return maxBrakingForce;
    }

    public void setMaxBrakingForce(float maxBrakingForce) {
        this.maxBrakingForce = maxBrakingForce;
    }

    public float getMaxStaticLoad() {
        return maxStaticLoad;
    }

    public void setMaxStaticLoad(float maxStaticLoad) {
        this.maxStaticLoad = maxStaticLoad;
    }

    public float getMinAirPressure() {
        return minAirPressure;
    }

    public void setMinAirPressure(float minAirPressure) {
        this.minAirPressure = minAirPressure;
    }

    public float getMinAirTemperature() {
        return minAirTemperature;
    }

    public void setMinAirTemperature(float minAirTemperature) {
        this.minAirTemperature = minAirTemperature;
    }

    public float getMaxAirTemperature() {
        return maxAirTemperature;
    }

    public void setMaxAirTemperature(float maxAirTemperature) {
        this.maxAirTemperature = maxAirTemperature;
    }
}
