package Vehicles;

import javax.swing.*;
import java.util.Objects;

public class HybridAirplane extends Vehicle implements AirVehicleI,SeaVehicleI,LandVehicleI,Engine {

    private double fuel_consumption;
    private double average_life_span;
    private LandVehicle landVehicle;
    private SeaVehicle seaVehicle;
    private AirVehicle airVehicle;

    public HybridAirplane(String model,int passengers, double max_speed, ImageIcon picture, int wheel_count,
                          boolean sail_with_wind, String flag, double fuel_consumption, double average_life_span){
        super(model,passengers, max_speed, picture);
        landVehicle = new LandVehicle(model, passengers, max_speed, picture, wheel_count, "paved");
        seaVehicle = new SeaVehicle(model, passengers, max_speed, picture, sail_with_wind, flag);
        airVehicle = new AirVehicle(model, passengers, max_speed,picture, "military");
        setFuel_consumption(fuel_consumption);
        this.average_life_span = average_life_span;
        int[] lockVal = {1,1,1};
        this.setLockVal(lockVal);
    }
    public HybridAirplane(HybridAirplane target){
        super(target);
        this.landVehicle = target.landVehicle;
        this.seaVehicle = target.seaVehicle;
        this.airVehicle = target.airVehicle;
        this.average_life_span = target.average_life_span;
        this.fuel_consumption = target.fuel_consumption;
        int[] lockVal = {1,1,1};
        this.setLockVal(lockVal);
    }

    @Override
    public HybridAirplane clone() throws CloneNotSupportedException {
        return new HybridAirplane(this);
    }

    @Override
    public void move(double distance){
        synchronized (LandVehicle.class) {
            synchronized (SeaVehicle.class) {
                synchronized (AirVehicle.class) {
                    landVehicle.move(distance);
                    seaVehicle.move(distance);
                    airVehicle.move(distance);
                }
            }
        }
    }
    @Override
    public void setUse_type(String use_type) {
        airVehicle.setUse_type(use_type);
    }

    @Override
    public String getUse_type() {
        return airVehicle.getUse_type();
    }

    @Override
    public int getWheel_count() {
        return landVehicle.getWheel_count();
    }

    @Override
    public String getRoad_type() {
        return landVehicle.getRoad_type();
    }

    @Override
    public void setRoad_type(String road_type) {
        landVehicle.setRoad_type(road_type);
    }

    @Override
    public void setWheel_count(int wheel_count) {
        landVehicle.setWheel_count(wheel_count);
    }

    @Override
    public String getFlag() {
        return seaVehicle.getFlag();
    }

    @Override
    public Boolean getSail_with_wind() {
        return seaVehicle.getSail_with_wind();
    }

    @Override
    public void setSail_with_wind(boolean sail_with_wind) {
        seaVehicle.setSail_with_wind(sail_with_wind);
    }

    @Override
    public void setFlag(String flag) {
        seaVehicle.setFlag(flag);
    }

    @Override
    public double getFuel_consumption() {
        return fuel_consumption;
    }

    @Override
    public void setFuel_consumption(double fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    @Override
    public double getAverage_life_span() {
        return average_life_span;
    }

    @Override
    public boolean equals(Vehicle o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HybridAirplane that = (HybridAirplane) o;
        return Double.compare(that.fuel_consumption, fuel_consumption) == 0 && Double.compare(that.average_life_span, average_life_span) == 0 && Objects.equals(landVehicle, that.landVehicle) && Objects.equals(seaVehicle, that.seaVehicle) && Objects.equals(airVehicle, that.airVehicle);
    }

    @Override
    public String toString() {
        return "Hybrid Airplane: "+"<br>"+seaVehicle.toString()+"The wheel count is " + getWheel_count() +"<br>"+
                "The appropriate road type is " + getRoad_type()+ "<br>" + "The use type is: " + getUse_type() + "<br>"+"The fuel consumption is: " + getFuel_consumption() + "<br>"+"The average life span is: " +getAverage_life_span()+"<br>";
    }
}
