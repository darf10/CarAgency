package Vehicles;

import javax.swing.*;

public class Frigate extends SeaVehicle implements Engine, SeaVehicleI{
    private double fuel_consumption, average_life_span;
    public double getFuel_consumption() {
        return fuel_consumption;
    }

    public double getAverage_life_span() {
        return average_life_span;
    }

    public void setFuel_consumption(double fuel_consumption) {
            this.fuel_consumption = fuel_consumption;
    }

    public Frigate(){
        super("", 0, 0,null, false, "Israel");
        this.setFuel_consumption(500);
        this.average_life_span = 4;
    }
    public Frigate(String model, int passengers, double max_speed, ImageIcon image, boolean sail_with_wind, String flag){
        super(model, passengers, max_speed, image, sail_with_wind, flag);
        this.setFuel_consumption(500);
        this.average_life_span = 4;
    }
    public Frigate(Frigate target){
        super(target);
        this.average_life_span = target.average_life_span;
        this.fuel_consumption = target.fuel_consumption;
    }

    @Override
    public String toString(){
        return "Frigate: " +"<br>"+ super.toString()+ "The fuel consumption is: "+getFuel_consumption()+"<br>"+" and the average life span is: "+getAverage_life_span()+"<br>";
    }

    public boolean equals(Vehicle object){
        if(!(object instanceof Frigate)){
            return false;
        }
        if(super.equals(object)){
            return true;
        }
        return false;
    }
    @Override
    public Vehicle makeCopy() {
        return (Vehicle) new Frigate(this);
    }
}