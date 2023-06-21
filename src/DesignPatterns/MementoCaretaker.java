package DesignPatterns;

import System.Agency;

import javax.swing.*;

public class MementoCaretaker {
    private VehicleMemento[] stateStack;
    private int amount;

    public MementoCaretaker(){
        stateStack = new VehicleMemento[3];
        amount = 0;
    }

    public void saveMemento(VehicleMemento vehicleMemento)  {
        if (amount == 3){
            DeleteLastMemento();
            JOptionPane.showMessageDialog(null, "Up to 3 saves at a time, oldest save was deleted.");
            stateStack[2] = vehicleMemento;
        }
        else{
            stateStack[amount] = vehicleMemento;
            amount++;
        }
    }
    public VehicleMemento getMemento(){
        amount--;
        return stateStack[amount];
    }
    public void DeleteLastMemento(){
        stateStack[0] = stateStack[1];
        stateStack[1] = stateStack[2];
        stateStack[2] = null;

    }

    public int getAmount() {
        return amount;
    }
}