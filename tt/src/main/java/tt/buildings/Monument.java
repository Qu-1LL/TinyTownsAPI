package tt.buildings;

import tt.*;
import tt.buildings.monument.*;

public abstract class Monument extends Building {

    private static NewBuilding nb1 = () -> new MandrasPalace();
    private static NewBuilding[] monuments = {nb1};

    private int index;

    public Monument (String name, Resource[][] schematic, int index) {
        super(name,BuildingType.MONUMENT,schematic);
        this.index = index;
    }
    @Override
    public Monument build () {
        return (Monument) monuments[index].newBuilding();
    }
}
