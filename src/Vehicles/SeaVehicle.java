package Vehicles;

import javax.swing.*;
import java.util.concurrent.locks.ReentrantLock;

public class SeaVehicle extends Vehicle implements SeaVehicleI{
    private boolean sail_with_wind;
    private String flag;
    public SeaVehicle(){
        super();
        sail_with_wind = false;
        flag = "";
        int[] lockVal = {1,0,0};
        this.setLockVal(lockVal);
    }
    public SeaVehicle(String model, int passengers, double max_speed, ImageIcon image, boolean sail_with_wind, String flag)
    {
        super(model, passengers, max_speed, image);
        setFlag(flag);
        setSail_with_wind(sail_with_wind);
        int[] lockVal = {1,0,0};
        this.setLockVal(lockVal);
    }
    public SeaVehicle(SeaVehicle target){
        super(target);
        this.sail_with_wind = target.sail_with_wind;
        this.flag = target.flag;
    }
    @Override
    public void move(double distance){
        super.move(distance);
    }

    @Override
    public Vehicle makeCopy() {
        return (Vehicle) new SeaVehicle(this);
    }

    public void setSail_with_wind(boolean sail_with_wind){
        this.sail_with_wind=sail_with_wind;
    }
    public void setFlag(String flag){this.flag = flag;}
    public String getFlag(){return this.flag;}
    public Boolean getSail_with_wind(){return this.sail_with_wind;}

    @Override
    public String toString(){
        String output;
        output = "Under "+getFlag()+" flag"+"<br>";
        if(getSail_with_wind())
            output += " sails with the wind"+"<br>";
        else
            output += " sails without the wind"+"<br>";
        return super.toString()+output;
    }
    public boolean equals(Vehicle object) {
        if (!(object instanceof SeaVehicle))
            return false;
        SeaVehicle obj = (SeaVehicle)object;
        if(!super.equals(obj))
            return false;
        if (getFlag() == obj.getFlag() && getSail_with_wind() == obj.getSail_with_wind())
            return true;
        return false;
    }
}