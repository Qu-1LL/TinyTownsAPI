package tt.buildings;

import tt.*;
import tt.buildings.navy.Factory;

public abstract class Navy extends Building {

    private static NewBuilding nb1 = () -> new Factory();
    private static NewBuilding[] navyBuildings = {nb1};

    protected Resource gainResource (String name) {
        count++;
        return game.getTT().chooseResource(name,Resource.fullValues());
    }

    

    protected Game game;
    private int index;
    protected int count; // Used to tell game amount of resources on building
    // Usefule for post office, warehouse, etc

    public Navy (String name, Resource[][] schematic, int index, Game game) {
        super(name,BuildingType.NAVY,schematic);
        this.index = index;
        this.game = game;
    }
    @Override
    public Navy build () {
        return (Navy) navyBuildings[index].newBuilding();
    }

}