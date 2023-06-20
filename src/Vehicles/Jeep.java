package Vehicles;

import javax.swing.*;

public class Jeep extends LandVehicle implements Commercial, Engine{
    private String licence_type;
    private double fuel_consumption;
    private double average_life_span;

    public Jeep(){
        super("", 5, 0,null, 4, "dirt");
        licence_type = "MINI";
        setFuel_consumption(0);
        average_life_span = 0;
    }
    public Jeep(String model, double max_speed, ImageIcon image, double fuel_consumption, double average_life_span){
        super(model, 5, max_speed, image,4, "dirt");
        this.licence_type = "MINI";
        setFuel_consumption(fuel_consumption);
        this.average_life_span = average_life_span;
    }
    public String getLicense() {
        return licence_type;
    }
    public double getFuel_consumption() {
        return fuel_consumption;
    }
    public void setFuel_consumption(double fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }
    public double getAverage_life_span() {
        return average_life_span;
    }

    @Override
    public String toString(){
        return "Jeep: "+"<br>"+super.toString()+"Licence type is: "+getLicense()+"<br>"+"Fuel consumption is: "+getFuel_consumption()+"<br>"+"Average life span is: "+getAverage_life_span() + " years"+"<br>";
    }

    //@Override
    public boolean equals(Vehicle object) {
        if (! (object instanceof Jeep))
            return false;
        Jeep obj = (Jeep) object;
        if (!super.equals(obj))
            return false;
        return this.getLicense().equals(obj.getLicense()) && this.getFuel_consumption() == obj.getFuel_consumption();
    }

}
