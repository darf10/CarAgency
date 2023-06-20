package Vehicles;

import javax.swing.*;

public class PlayingGlider extends AirVehicle implements NonEngine {
    private final String power_source;
    private final char energy_type;

    public PlayingGlider() {
        super("", 0, 10, null, "civilian");
        this.energy_type = 'A';
        this.power_source = "manual";
    }

    public PlayingGlider(ImageIcon image) {
        super("toy", 0, 10, image, "civilian");
        this.energy_type = 'A';
        this.power_source = "manual";
    }

    public PlayingGlider(PlayingGlider target) {
        super(target);
        this.power_source = target.power_source;
        this.energy_type =target.energy_type;
    }

    @Override
    public PlayingGlider clone() throws CloneNotSupportedException {
        return new PlayingGlider(this);
    }

    @Override
    public String getPower_source() {
        return power_source;
    }

    @Override
    public char getEnergy_type() {
        return energy_type;
    }

    @Override
    public String toString() {
        return "Playing Glider: " + "<br>" + super.toString() + "The energy type is: " + getEnergy_type() + "<br>" + "The power source is: " + getPower_source() + "<br>";
    }

    public boolean equals(Vehicle object) {
        if (!(object instanceof PlayingGlider))
            return false;
        PlayingGlider obj = (PlayingGlider) object;
        if (!super.equals(obj))
            return false;
        return this.getPower_source() == obj.getPower_source() && this.getEnergy_type() == obj.getEnergy_type();
    }
}
