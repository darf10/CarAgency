package Vehicles;

import javax.swing.*;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class LandVehicle extends Vehicle implements LandVehicleI{
    private int wheel_count;
    private String road_type;
    public LandVehicle(){
        super();
        wheel_count = 0;
        road_type = "";
    }
    public LandVehicle(String model, int passengers, double max_speed, ImageIcon image, int wheel_count, String road_type){
        super(model, passengers, max_speed, image);
        this.wheel_count = wheel_count;
        this.road_type = road_type;
    }
    public LandVehicle(LandVehicle target){
        super(target);
        this.wheel_count = target.wheel_count;
        this.road_type = target.road_type;
    }
    @Override
    public void move(double distance){
        super.move(distance);
    }

    @Override
    public Vehicle makeCopy() {
        return (Vehicle) new LandVehicle(this);
    }

    public int getWheel_count() {
        return wheel_count;
    }
    public String getRoad_type() {
        return road_type;
    }
    @Override
    public String toString(){
        return super.toString() + "The wheel count is " + this.getWheel_count() +
                "<br>"+ "The appropriate road type is " + this.getRoad_type()+"<br>";
    }

    @Override
    public boolean equals(Vehicle object) {
        if (!(object instanceof LandVehicle))
            return false;
        LandVehicle obj = (LandVehicle)object;
        if(!super.equals(obj))
            return false;
        if(getWheel_count() == obj.getWheel_count() && getRoad_type() == obj.getRoad_type())
            return true;
        return false;
    }


    public void setWheel_count(int wheel_count){
        this.wheel_count = wheel_count;
    }
    public void setRoad_type(String road_type){
        this.road_type = road_type;
    }
}