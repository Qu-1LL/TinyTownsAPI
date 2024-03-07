package tt.buildings;

import tt.Building;
import tt.BuildingType;
import tt.Resource;
import tt.Foundation;

public class UniqueBuilding extends Foundation implements Building {

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
        return "This building scores 1 point.\n" +
        "This building is only to be used for testing purposes.\n";
    }
    @Override
    public int getScore() {
        return 1;
    }

    public static void main (String[] Args) {
        Resource[][] schem = new Resource[2][2];
        schem[0][0] = Resource.EMPTY;
        schem[0][1] = Resource.BRICK;
        schem[1][0] = Resource.EMPTY;
        schem[1][1] = Resource.GLASS;
        Building build1 = new UniqueBuilding(schem);
        System.out.println(build1);

        Building build2 = new UniqueBuilding();
        System.out.println(build2);
    }
}