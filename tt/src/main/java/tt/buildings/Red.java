package tt.buildings;

import tt.*;
import tt.buildings.red.Farm;

public class Red extends Building {

    private static NewBuilding nb1 = () -> new Farm();
    private static NewBuilding[] redBuildings = {nb1};
    

    private int index;

    public Red (String name, Resource[][] schematic, int index) {
        super(name,BuildingType.RED,schematic);
        this.index = index;
    }
    @Override
    public Red build () {
        return (Red) redBuildings[index].newBuilding();
    }

}