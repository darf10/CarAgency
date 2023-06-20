package Vehicles;

import javax.swing.*;
import java.util.Objects;

public class Bicycle extends TwoWheeledVehicle implements NonEngine{
    private String power_source;
    private char energy_type;

    public Bicycle(String model,int passengers, double max_speed,ImageIcon image, String road_type){
        super(model, passengers, max_speed, image, road_type);
        setPower_source("manual");
        setEnergy_type('A');
    }
    public Bicycle(Bicycle target){
        super(target);
        this.power_source = target.power_source;
        this.energy_type = target.energy_type;
    }

    @Override
    public String getPower_source() {
        return power_source;
    }

    @Override
    public char getEnergy_type() {
        return energy_type;
    }

    public void setPower_source(String power_source) {
        this.power_source = power_source;
    }

    public void setEnergy_type(char energy_type) {
        this.energy_type = energy_type;
    }

    @Override
    public Bicycle clone() throws CloneNotSupportedException {
        return new Bicycle(this);
    }

    @Override
    public boolean equals(Vehicle o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicycle bicycle = (Bicycle) o;
        return energy_type == bicycle.energy_type && power_source.equals(bicycle.power_source);
    }
    @Override
    public String toString(){
        return "Bicycle: "+"<br>"+super.toString()+" The energy type is: "+getEnergy_type()+"<br>"+"The power source is: "+getPower_source()+"<br>";
    }
}
