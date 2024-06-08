package tt.buildings;

import tt.*;
import tt.buildings.orange.Chapel;

public class Orange extends Building {

    private static NewBuilding nb1 = () -> new Chapel();
    private static NewBuilding[] orangeBuildings = {nb1};
    

    private int index;

    public Orange (String name, Resource[][] schematic, int index) {
        super(name,BuildingType.ORANGE,schematic);
        this.index = index;
    }
    @Override
    public Orange build () {
        return (Orange) orangeBuildings[index].newBuilding();
    }

}
