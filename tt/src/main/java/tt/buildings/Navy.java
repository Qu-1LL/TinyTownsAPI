package tt.buildings;

import tt.*;
import tt.buildings.navy.Factory;

public abstract class Navy extends Building {

    private static NewBuilding nb1 = () -> new Factory();
    private static NewBuilding[] navyBuildings = {nb1};

    protected Player player;
    protected TT tt;

    protected abstract void initialize (TT tt, Player player);
    //Handles the trigger caused by round resource
    public Resource handleTrigger (Resource roundResource, String username) {
        return null;
    }
    //Checks to see if the round resource causes a trigger in the given navy
    public boolean isTriggered (Resource roundResource, String username) {
        return false;
    }


    private int index;
    private boolean needsTT;

    public Navy (String name, Resource[][] schematic, int index, boolean needsTT) {
        super(name,BuildingType.NAVY,schematic);
        this.index = index;
        this.needsTT = needsTT;
    }

    public boolean needsTT () {
        return needsTT;
    }
    @Override
    public Navy build () {
        return (Navy) navyBuildings[index].newBuilding();
    }

}