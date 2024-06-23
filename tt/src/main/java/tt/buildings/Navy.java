package tt.buildings;

import tt.*;
import tt.buildings.navy.Factory;

public abstract class Navy extends Building {

    private static NewBuilding nb1 = () -> new Factory();
    private static NewBuilding[] navyBuildings = {nb1};

    protected void initialize (String name) {}
    //Handles the trigger causes by round resource
    public Resource handleTrigger (Resource roundResource, String username) {
        return null;
    }
    //Checks to see if the round resource causes a trigger in the given navy
    public boolean isTriggered (Resource roundResource, String username) {
        return false;
    }

    private int index;

    public Navy (String name, Resource[][] schematic, int index) {
        super(name,BuildingType.NAVY,schematic);
        this.index = index;
    }
    @Override
    public Navy build () {
        return (Navy) navyBuildings[index].newBuilding();
    }

}