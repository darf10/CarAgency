package Vehicles;

import javax.swing.*;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class AirVehicle extends Vehicle implements AirVehicleI{


    private String use_type;
    public AirVehicle(){
        super();
        use_type = "civilian";
        int[] lockVal = {0,1,0};
        this.setLockVal(lockVal);
    }
    public AirVehicle(String model, int passengers, double max_speed,ImageIcon image, String use_type)
    {
        super(model, passengers, max_speed, image);
        setUse_type(use_type);
        int[] lockVal = {0,1,0};
        this.setLockVal(lockVal);
    }
    public AirVehicle (AirVehicle target){
        super(target);
        this.use_type = target.use_type;
    }
    @Override
    public void move(double distance){
        synchronized (AirVehicle.class) {
            super.move(distance);
        }
    }

    @Override
    public Vehicle makeCopy() {
        return (Vehicle)new AirVehicle(this) ;
    }

    public void setUse_type(String use_type) {this.use_type = use_type; }
    public String getUse_type(){return use_type;}
    @Override
    public String toString(){
        return super.toString()+"The air vehicle is for "+getUse_type()+ " purpose"+"<br>";
    }

    @Override
    public boolean equals(Vehicle o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirVehicle that = (AirVehicle) o;
        return Objects.equals(use_type, that.use_type);
    }
}
