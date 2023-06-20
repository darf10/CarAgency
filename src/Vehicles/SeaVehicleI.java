package Vehicles;

public interface SeaVehicleI {
    public void move(double distance);
    public String getFlag();
    public Boolean getSail_with_wind();
    public void setSail_with_wind(boolean sail_with_wind);
    public void setFlag(String flag);
}
