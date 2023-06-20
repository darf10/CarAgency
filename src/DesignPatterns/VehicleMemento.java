package DesignPatterns;

import Vehicles.*;

public class VehicleMemento {
    private VehicleDecorator[] vehicles;

    public VehicleMemento (VehicleDecorator[] vehicles) throws CloneNotSupportedException {
        this.vehicles = new VehicleDecorator[vehicles.length];
        for (int i = 0; i < vehicles.length; i++) {
            this.vehicles[i] = vehicles[i].makeCopy();
        }
    }
    public VehicleDecorator[] getMemento() {
        return vehicles;
    }

}