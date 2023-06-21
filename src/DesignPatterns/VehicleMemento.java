package DesignPatterns;

import Vehicles.*;

public class VehicleMemento {
    private VehicleDecorator[] vehicles;
    private int size;
    private double totalDistance;

    public VehicleMemento(VehicleDecorator[] vehicles, int size, double totalDistance) {
        this.vehicles = new VehicleDecorator[vehicles.length];
        for (int i = 0; i < vehicles.length; i++) {
            this.vehicles[i] = vehicles[i].makeCopy();
        }
        this.size = size;
        this.totalDistance = totalDistance;
    }

    public VehicleDecorator[] getVehicles() {
        return vehicles;
    }
    public VehicleDecorator getVehicleAt(int index) {
        return vehicles[index];
    }
    public int getSize(){
        return size;
    }
    public double getTotalDistance(){
        return totalDistance;
    }

}