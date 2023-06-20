package Vehicles;

import javax.swing.*;
import java.util.Objects;

public class ElectricBicycle extends TwoWheeledVehicle implements Engine{
    private double fuel_consumption;
    private double average_life_span;

    public ElectricBicycle(String model,int passengers, double max_speed,ImageIcon image, String road_type,double average_life_span ){
        super(model, passengers, max_speed, image, road_type);
        setFuel_consumption(20);
        this.average_life_span = average_life_span;
    }



    public ElectricBicycle (ElectricBicycle target){
        super(target);
        this.fuel_consumption = target.fuel_consumption;
        this.average_life_span = target.average_life_span;
    }
    public double getFuel_consumption(){ return fuel_consumption;}
    public void setFuel_consumption(double fuel_consumption){this.fuel_consumption = fuel_consumption;}
    public double getAverage_life_span(){return average_life_span;}
    @Override
    public ElectricBicycle clone() throws CloneNotSupportedException {
        return new ElectricBicycle(this);
    }
    @Override
    public boolean equals(Vehicle o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectricBicycle that = (ElectricBicycle) o;
        return Double.compare(that.fuel_consumption, fuel_consumption) == 0 && Double.compare(that.average_life_span, average_life_span) == 0;
    }

    @Override
    public Vehicle makeCopy() {
        return (Vehicle) new ElectricBicycle(this);
    }

    @Override
    public String toString(){
        return "Electric bicycle: "+"<br>"+super.toString()+"Fuel consumption is: "+getFuel_consumption()+"<br>"+"Average life span is: "+getAverage_life_span() + " years"+"<br>";
    }
}
