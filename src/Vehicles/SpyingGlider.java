package Vehicles;

import javax.swing.*;

public class SpyingGlider extends AirVehicle implements NonEngine, Cloneable {
    private final String power_source;
    private final char energy_type;
    public String getPower_source(){
        return power_source;
    }
    public char getEnergy_type(){
        return energy_type;
    }
    public SpyingGlider(ImageIcon image,String power_source){
        super("Classified", 1, 50,image, "Military");
        this.energy_type = 'C';
        this.power_source = power_source;
    }
    public SpyingGlider(SpyingGlider target){
        super(target);
        this.energy_type = target.energy_type;
        this.power_source = target.power_source;
    }
    @Override
    public String toString(){
        return "Spying Glider: " +"<br>"+ super.toString() + "The power source is " + this.getPower_source()+"<br>" +
                "The energy type is " + this.getEnergy_type()+"<br>";
    }
    public boolean equals(Vehicle object){
        if(!(object instanceof  SpyingGlider)){
            return false;
        }
        SpyingGlider obj = (SpyingGlider)object;
        return super.equals(object) && this.getPower_source() == obj.getPower_source();
    }

    @Override
    public SpyingGlider clone() throws CloneNotSupportedException {
        return new SpyingGlider(this);
    }
}