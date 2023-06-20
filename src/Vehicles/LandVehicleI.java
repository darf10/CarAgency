package Vehicles;

public interface LandVehicleI {
    public void move(double distance);
    public int getWheel_count();
    public String getRoad_type();
    public void setRoad_type(String road_type);
    public void setWheel_count(int wheel_count);
}
