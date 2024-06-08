package tt.buildings.grey;

import tt.*;
import tt.buildings.grey.buildings.*;

public class Grey extends Building {

    private static NewBuilding nb1 = () -> new Well();
    private static NewBuilding[] greyBuildings = {nb1};
    

    private int index;

    public Grey (String name, Resource[][] schematic, int index) {
        super(name,BuildingType.GREY,schematic);
        this.index = index;
    }
    @Override
    public Grey build () {
        return (Grey) greyBuildings[index].newBuilding();
    }

}