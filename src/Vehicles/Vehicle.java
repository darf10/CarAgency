package Vehicles;
import System.*;
import javax.swing.*;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.lang.Cloneable;


public abstract class Vehicle implements Cloneable{
    private double distance;
    private String model;
    private int passengers;
    private double max_speed;
    private ImageIcon image;
    private final ReentrantLock lock;
    private int[] lock_val;
    public Vehicle(){
        this.model = "";
        this.distance = 0;
        this.passengers = 0;
        this.max_speed = 0;
        this.image = new ImageIcon();
        this.lock = new ReentrantLock();
    }
    public Vehicle(String model,int passengers, double max_speed, ImageIcon picture){
        this.distance = 0;
        this.passengers = passengers;
        this.max_speed = max_speed;
        this.model = model;
        this.image = picture;
        this.lock = new ReentrantLock();
    }
    public Vehicle(Vehicle obj){
        this.distance = obj.distance;
        this.model = obj.model;
        this.passengers = obj.passengers;
        this.max_speed = obj.max_speed;
        this.image = obj.image;
        this.lock = obj.lock;
        this.lock_val = obj.lock_val;
    }
    @Override
    public String toString(){
        return "Model: " + model + "<br>"+"Traveled: " + distance + " Km" +
                "<br>"+ "Max speed of " + max_speed + " Mph"+"<br>"+" Can carry max of " + passengers + " people"+"<br>";
    }

    public boolean equals(Vehicle obj){
        return this.distance == obj.distance &&
                Objects.equals(this.model, obj.model) &&
                this.passengers == obj.passengers &&
                this.max_speed == obj.max_speed;
    }
    public double getMax_speed(){
        return max_speed;
    }

    @Override
    public Vehicle clone() throws CloneNotSupportedException {
        return (Vehicle) super.clone();
    }

    public void move(double distance){
        this.distance += distance;
        Agency.addTotalDistance(distance);
    }
    public String getModel(){
        return model;
    }
    public double getDistance(){
        return distance;
    }
    public int getPassengers(){
        return passengers;
    }
    public void reset_distance(){
        this.distance = 0;
    }
    public ImageIcon getImage(){return image;}
    public ReentrantLock getLock(){return lock;}
    public int[] getLockVal(){return lock_val;}
    public void setLockVal(int[] val){lock_val = val;}

}