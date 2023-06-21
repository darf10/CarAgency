package DesignPatterns;

import Vehicles.*;

public class VehicleMemento {
    private VehicleDecorator[] vehicles;
    private int size;

    public VehicleMemento(VehicleDecorator[] vehicles) {
        this.vehicles = new VehicleDecorator[vehicles.length];
        for (int i = 0; i < vehicles.length; i++) {
            this.vehicles[i] = vehicles[i].makeCopy();
        }
        this.size = vehicles.length;

    }

    public VehicleDecorator[] getMemento() {
        return vehicles;
    }
    public int getSize(){
        return size;
    }

}