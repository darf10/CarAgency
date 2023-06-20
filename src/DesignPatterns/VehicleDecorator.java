package DesignPatterns;

import Vehicles.*;

import javax.swing.*;
import java.util.concurrent.locks.ReentrantLock;

public class VehicleDecorator implements VehicleI {
    private Vehicle vehicle;
    private String color;
    private String status;

    public VehicleDecorator(Vehicle vehicle, String color){
        this.vehicle = vehicle;
        this.color = color;
        this.setStatus("Available");
    }
    public VehicleDecorator(VehicleDecorator target){
        this.vehicle = target.vehicle;
        this.color = target.color;
        this.status = target.status;
    }

    @Override
    protected VehicleDecorator clone() throws CloneNotSupportedException {
        return new VehicleDecorator(this);
    }

    public void setStatus(String status){
        this.status = status;
    }
    public Vehicle getVehicle(){return vehicle;}
    public String getColor(){return color;}
    public String getStatus(){synchronized(this){return status;}}

    @Override
    public String toString(){
        return "Status: " + this.status + "<br>" + this.vehicle.toString();
    }

    @Override
    public boolean equals(Vehicle obj) {
        return this.vehicle.equals(obj);
    }

    @Override
    public double getMax_speed() {
        return this.vehicle.getMax_speed();
    }

    @Override
    public void move(double distance) {
        this.vehicle.move(distance);
    }

    @Override
    public String getModel() {
        return this.vehicle.getModel();
    }

    @Override
    public double getDistance() {
        return this.vehicle.getDistance();
    }

    @Override
    public int getPassengers() {
        return this.vehicle.getPassengers();
    }

    @Override
    public void reset_distance() {
        this.vehicle.reset_distance();
    }

    @Override
    public ImageIcon getImage() {
        return this.vehicle.getImage();
    }

    @Override
    public ReentrantLock getLock() {
        return this.vehicle.getLock();
    }
}
