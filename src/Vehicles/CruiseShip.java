package Vehicles;

import javax.swing.*;
import java.util.Objects;

public class CruiseShip extends SeaVehicle implements Engine, Commercial{
    private double fuel_consumption, average_life_span;
    private String licence_type;

    CruiseShip(){
        super("", 0, 0,null, true, "Israel");
        setFuel_consumption(0);
        setLicence_type("Unlimited");
        setAverage_life_span(0);
    }
    public CruiseShip(String model, int passengers, double max_speed,ImageIcon image, double fuel_consumption, double average_life_span, String flag){
        super(model, passengers, max_speed, image, true, flag);
        setFuel_consumption(fuel_consumption);
        setLicence_type("Unlimited");
        setAverage_life_span(average_life_span);
    }



    public CruiseShip(CruiseShip target){
        super(target);
        this.fuel_consumption = target.fuel_consumption;
        this.average_life_span = target.average_life_span;
        this.licence_type = target.licence_type;
    }
    @Override
    public String getLicense() {
        return licence_type;
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
    public void setFuel_consumption(double fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    public void setAverage_life_span(double average_life_span) {
        this.average_life_span = average_life_span;
    }

    public void setLicence_type(String licence_type) {
        this.licence_type = licence_type;
    }

    @Override
    public String toString() {
        return "Cruise Ship: " +"<br>"+ super.toString()+ "The fuel consumption is: "+getFuel_consumption()+"<br>"+"The average life span is: "+getAverage_life_span()+"<br>"+"The licence type is: "+getLicense()+"<br>";
    }

    @Override
    public boolean equals(Vehicle o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CruiseShip that = (CruiseShip) o;
        return Double.compare(that.fuel_consumption, fuel_consumption) == 0 && Double.compare(that.average_life_span, average_life_span) == 0 && licence_type.equals(that.licence_type);
    }
    @Override
    public Vehicle makeCopy() {
        return (Vehicle) new CruiseShip(this);
    }
}
