package tt.buildings.navy;

import tt.*;
import tt.buildings.navy.buildings.*;

public class Navy extends Building {

    private static NewBuilding nb1 = () -> new Factory();
    private static NewBuilding[] navyBuildings = {nb1};

    protected Resource grabResource () {
        count++;
        //somehow interect with game to get a resource from player
        return Resource.EMPTY;
    }

    private int index;
    private int count; // Used to tell game amount of resources on building
    // Usefule for post office, warehouse, etc

    public Navy (String name, Resource[][] schematic, int index) {
        super(name,BuildingType.NAVY,schematic);
        this.index = index;
    }
    @Override
    public Navy build () {
        return (Navy) navyBuildings[index].newBuilding();
    }

}