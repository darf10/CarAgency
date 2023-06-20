package DesignPatterns;

import System.Agency;

public class MementoCaretaker {
    private VehicleMemento[] stateStack;
    private int amount;

    public MementoCaretaker(){
        stateStack = new VehicleMemento[3];
        amount = 0;
    }

    public void saveMemento(VehicleMemento vehicleMemento) throws CloneNotSupportedException {
        if (amount == 3){
            DeleteLastMemento();
            stateStack[2] = vehicleMemento;
        }
        else{
            stateStack[amount] = vehicleMemento;
            amount++;
        }
    }
    public void getMemento(Agency agency){
        if (amount != 0) {
            amount--;
            agency.restoreMemento(stateStack[amount]);
        }
    }
    public void DeleteLastMemento(){
        stateStack[0] = stateStack[1];
        stateStack[1] = stateStack[2];
        stateStack[2] = null;

    }
}