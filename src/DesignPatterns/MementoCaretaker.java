package DesignPatterns;


public class MementoCaretaker {
    private VehicleMemento[][] stateStack;
    private int amount;

    public MementoCaretaker(){
        stateStack = new VehicleMemento[3][];
        amount = 0;
    }

    public void addMemento(VehicleMemento[] vehicles) {
        if (amount == 3){
            DeleteLastMemento();
            stateStack[2] = vehicles;
        }
        else{
            stateStack[amount] = vehicles;
            amount++;
        }

    }
    public VehicleMemento[] getMemento(){
        amount--;
        VehicleMemento[] temp = stateStack[amount];
        stateStack[amount] = null;
        return temp;
    }
    public void DeleteLastMemento(){
        stateStack[0] = stateStack[1];
        stateStack[1] = stateStack[2];
        stateStack[2] = null;

    }
}
