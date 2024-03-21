package tt.buildings;

import tt.*;

public class UniqueBuilding extends Building implements Holdable {

    public UniqueBuilding (BuildingType type, Resource[][] schematic,int size) {
        super("Unique Building",type,schematic,size);
    }
    public UniqueBuilding (Resource[][] schematic,int size) {
        super("Unique Building",null,schematic,size);
    }
    public UniqueBuilding (BuildingType type) {
        super("Unique Building",type,new Resource[1][1],1);
        this.getSchematic()[0][0] = Resource.BRICK;
    }
    public UniqueBuilding () {
        super("Unique Building",null,new Resource[1][1],1);
        this.getSchematic()[0][0] = Resource.BRICK;
    }
    
    @Override
    public String getRules () {
        return "This building scores 1 point.\n" +
        "This building is only to be used for testing purposes.\n";
    }
    @Override
    public int getScore(Town town, int x, int y) {
        return 1;
    }

}