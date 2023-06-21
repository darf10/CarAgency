package Vehicles;

import javax.swing.*;
import java.util.Objects;

public class Amphibious extends Vehicle implements LandVehicleI,SeaVehicleI, Engine{
    private final LandVehicle landVehicle;
    private final SeaVehicle seaVehicle;
    private double fuel_consumption;
    private double average_life_span;

    Amphibious(){
        super();
        landVehicle = new LandVehicle();
        seaVehicle = new SeaVehicle();
        setFuel_consumption(0);
        setAverage_life_span(0);
    }
    public Amphibious(String model, int passengers, double max_speed, ImageIcon image, boolean sail_with_wind, int wheel_count, double fuel_consumption, double average_life_span, String flag){
        super(model, passengers, max_speed, image);
        landVehicle = new LandVehicle(model, passengers, max_speed, image, wheel_count, "paved");
        seaVehicle = new SeaVehicle(model, passengers, max_speed, image, sail_with_wind, flag);
        setFuel_consumption(fuel_consumption);
        setAverage_life_span(average_life_span);
    }

    public Amphibious(Amphibious target){
        super(target);
        this.landVehicle = new LandVehicle(target.landVehicle);
        this.seaVehicle = new SeaVehicle(target.seaVehicle);
        this.fuel_consumption = target.fuel_consumption;
        this.average_life_span = target.average_life_span;
    }
    @Override
    public void move(double distance){
        super.move(distance);
    }

    @Override
    public Vehicle makeCopy() {
        return (Vehicle) new Amphibious(this);
    }

    @Override
    public double getFuel_consumption() {
        return fuel_consumption;
    }

    @Override
    public double getAverage_life_span() {
        return average_life_span;
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
    public String getFlag() {
        return seaVehicle.getFlag();
    }

    @Override
    public Boolean getSail_with_wind() {
        return seaVehicle.getSail_with_wind();
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
    public void setFuel_consumption(double fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    public void setAverage_life_span(double average_life_span) {
        this.average_life_span = average_life_span;
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
    public boolean equals(Vehicle o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amphibious that = (Amphibious) o;
        return Double.compare(that.fuel_consumption, fuel_consumption) == 0 && Double.compare(that.average_life_span, average_life_span) == 0 && landVehicle.equals(that.landVehicle);
    }

    @Override
    public String toString() {
        String output;
        output = "Under "+getFlag()+" flag"+"<br>";
        if(getSail_with_wind())
            output += " sails with the wind"+"<br>";
        else
            output += " sails without the wind"+"<br>";
        return "Amphibious: "+"<br>"+super.toString()+output+"The wheel count is " + getWheel_count() +"<br>"+
                "The appropriate road type is " + getRoad_type()+"<br>"+"The fuel consumption is: "+getFuel_consumption()+"<br>"+"The average life span is: "+getAverage_life_span()+"<br>";
    }
    @Override
    public Amphibious clone() throws CloneNotSupportedException {
        return new Amphibious(this);
    }
}
