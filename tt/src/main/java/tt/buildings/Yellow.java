package tt.buildings;

import tt.*;
import tt.buildings.yellow.Theatre;

public abstract class Yellow extends Building {

    private static NewBuilding nb1 = () -> new Theatre();
    private static NewBuilding[] yellowBuildings = {nb1};
    

    private int index;

    public Yellow (String name, Resource[][] schematic, int index) {
        super(name,BuildingType.YELLOW,schematic);
        this.index = index;
    }
    @Override
    public Yellow build () {
        return (Yellow) yellowBuildings[index].newBuilding();
    }

}
