package Vehicles;

import javax.swing.*;
import java.util.Objects;

public abstract class TwoWheeledVehicle extends LandVehicle{

    public TwoWheeledVehicle(String model, int passengers, double max_speed, ImageIcon image, String road_type) {
        super(model, passengers, max_speed, image, 2, road_type);
    }
    public TwoWheeledVehicle(TwoWheeledVehicle target) {
        super(target);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
