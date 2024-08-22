package tt.buildings;

import tt.*;
import tt.buildings.green.Tavern;

public abstract class Green extends Building {

    private static NewBuilding nb1 = () -> new Tavern();
    private static NewBuilding[] greenBuildings = {nb1};

    private int index;

    public Green (String name, Resource[][] schematic, int index) {
        super(name,BuildingType.GREEN,schematic);
        this.index = index;
    }
    @Override
    public Green build () {
        return (Green) greenBuildings[index].newBuilding();
    }

}