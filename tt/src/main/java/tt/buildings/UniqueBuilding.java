package tt.buildings;

import tt.*;

public class UniqueBuilding extends Building {

    public UniqueBuilding (BuildingType type, Resource[][] schematic) {
        super("Unique Building",type,schematic);
    }
    public UniqueBuilding (Resource[][] schematic) {
        super("Unique Building",null,schematic);
    }
    public UniqueBuilding (BuildingType type) {
        super("Unique Building",type,new Resource[1][1]);
        this.getSchematic()[0][0] = Resource.BRICK;
    }
    public UniqueBuilding () {
        super("Unique Building",null,new Resource[1][1]);
        this.getSchematic()[0][0] = Resource.BRICK;
    }
    
    @Override
    public String getRules () {
        return "This building scores 1 point. " +
        "This building is only to be used for testing purposes.\n";
    }
    @Override
    public int getScore(Town town, int x, int y) {
        return 1;
    }

}