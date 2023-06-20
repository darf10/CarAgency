package Vehicles;

import javax.swing.*;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public interface VehicleI {
    public String toString();

    public boolean equals(Vehicle obj);
    public double getMax_speed();
    public void move(double distance);
    public String getModel();
    public double getDistance();
    public int getPassengers();
    public void reset_distance();
    public ImageIcon getImage();
    public ReentrantLock getLock();

}
