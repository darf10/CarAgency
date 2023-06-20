package System;

import DesignPatterns.*;
import Vehicles.*;

import javax.swing.*;
import java.util.*;

public class Agency {
    private VehicleDecorator[] vehicles;
    private int size;
    private static double totalDistance = 0;
    public Agency() {
        vehicles = null;
        StaticLocks init = new StaticLocks();
    }
    public void BuildJeep(String model, double max_speed,ImageIcon image, double fuel_consumption, double average_life_span,String color) {
        Jeep obj = new Jeep(model, max_speed, image, fuel_consumption, average_life_span);
        VehicleDecorator temp = new VehicleDecorator(obj, color);
        ExtendVehicleArr();
        vehicles[size - 1] = temp;
    }

    public void BuildFrigate(String model, int passengers, double max_speed,ImageIcon image, boolean sail_with_wind, String flag,String color) {
        Frigate obj = new Frigate(model,passengers,max_speed,image ,sail_with_wind, flag);
        VehicleDecorator temp = new VehicleDecorator(obj, color);
        ExtendVehicleArr();
        vehicles[size - 1] = temp;
    }

    public void BuildSpyingGlider(ImageIcon image,String power_source,String color) {
        SpyingGlider obj = new SpyingGlider(image, power_source);
        VehicleDecorator temp = new VehicleDecorator(obj, color);
        ExtendVehicleArr();
        vehicles[size - 1] = temp;
    }

    public void BuildPlayingGlider(ImageIcon image, String color) {
        PlayingGlider obj = new PlayingGlider(image);
        VehicleDecorator temp = new VehicleDecorator(obj, color);
        ExtendVehicleArr();
        vehicles[size - 1] = temp;
    }

    public void BuildBicycle(String model,int passengers, double max_speed,ImageIcon image, String road_type,String color){
        Bicycle obj = new Bicycle(model,passengers, max_speed, image, road_type);
        VehicleDecorator temp = new VehicleDecorator(obj, color);
        ExtendVehicleArr();
        vehicles[size - 1] = temp;
    }

    public void BuildAmphibious(String model, int passengers, double max_speed,ImageIcon image, boolean sail_with_wind, int wheel_count, double fuel_consumption, double average_life_span, String flag,String color){
        Amphibious obj = new Amphibious(model, passengers, max_speed,image,  sail_with_wind,  wheel_count,  fuel_consumption, average_life_span, flag);
        VehicleDecorator temp = new VehicleDecorator(obj, color);
        ExtendVehicleArr();
        vehicles[size - 1] = temp;
    }
    public void BuildCruiseShip(String model, int passengers, double max_speed, ImageIcon image, double fuel_consumption, double average_life_span, String flag, String color){
        CruiseShip obj = new CruiseShip(model,  passengers,  max_speed, image, fuel_consumption,  average_life_span, flag);
        VehicleDecorator temp = new VehicleDecorator(obj, color);
        ExtendVehicleArr();
        vehicles[size - 1] = temp;
    }
    public void BuildHybridAirplane(String model,int passengers, double max_speed, ImageIcon image, int wheel_count,
                                    boolean sail_with_wind, String flag, double fuel_consumption, double average_life_span, String color){
        HybridAirplane obj = new HybridAirplane(model,passengers, max_speed, image, wheel_count,
                sail_with_wind, flag, fuel_consumption, average_life_span);
        VehicleDecorator temp = new VehicleDecorator(obj, color);
        ExtendVehicleArr();
        vehicles[size-1] = temp;
    }
    public void BuildElectricBicycle(String model,int passengers, double max_speed,ImageIcon image, String road_type,double average_life_span, String color){
        ElectricBicycle obj = new ElectricBicycle(model,passengers, max_speed, image, road_type, average_life_span);
        VehicleDecorator temp = new VehicleDecorator(obj, color);
        ExtendVehicleArr();
        vehicles[size-1] = temp;
    }
    public int FindIndex(VehicleDecorator vehicle) {
        for (int i = 0; i < size; i++) {
            if (vehicle.equals(vehicles[i])) {
                return i;
            }
        }
        return -1;

    }

    public int getSize(){ return size;}
    public VehicleDecorator getVehicleAt(int index){ return vehicles[index]; }
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(vehicles[i]);
        }
    }

    public void ExtendVehicleArr(){
        VehicleDecorator[] temp = new VehicleDecorator[size+1];
        for (int i=0;i<size;i++)
            temp[i] = vehicles[i];
        vehicles=temp;
        size++;
    }
    public synchronized void DeleteVehicle(int index) {
        setTotalDistance(-this.getVehicleAt(index).getDistance());
        if (size == 1) {
            vehicles[size - 1] = null;
        } else {
            VehicleDecorator[] temp = new VehicleDecorator[size - 1];
            for (int i = 0; i < size; i++) {
                if (i < index) {
                    temp[i] = vehicles[i];
                } else if (i == size - 1) {
                    break;
                } else {
                    temp[i] = vehicles[i + 1];
                }
            }
            vehicles = temp;
        }
        size--;
    }
    public double getTotalDistance(){return totalDistance;}
    public static void setTotalDistance(double distance){
        totalDistance += distance;
    }

    public VehicleMemento createMemento() throws CloneNotSupportedException {
        return new VehicleMemento(vehicles);
    }
    public void restoreMemento(VehicleDecorator[] vehicles){
        this.vehicles = vehicles;
    }
}