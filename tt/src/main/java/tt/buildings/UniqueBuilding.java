package tt.buildings;

import tt.Building;
import tt.BuildingType;
import tt.Resource;
import tt.Color;

public class UniqueBuilding extends Color implements Building {
    private final Resource[][] schematic;


    public UniqueBuilding (BuildingType type, Resource[][] schematic) {
        super(type);
        this.schematic = schematic;
    }
    public UniqueBuilding (Resource[][] schematic) {
        super(null);
        this.schematic = schematic;
    }
    public UniqueBuilding (BuildingType type) {
        super(type);
        this.schematic = new Resource[1][1];
        schematic[0][0] = Resource.BRICK;
    }
    public UniqueBuilding () {
        super(null);
        this.schematic = new Resource[1][1];
        schematic[0][0] = Resource.BRICK;
    }
    public String getRules () {
        return "This building scores 1 point.\nTo only be used for testing purposes.\n";
    }
    public Resource[][] getSchematic() {
        return schematic;
        /*this code is not good, since technically references to locations
         * inside the array could edit the buildings schematic. Should be 
         * function using Arrays.copyOf(schematic) that successfully 
         * returns a double array, but copyOf only works with single arrays.
         */
    }
    public int getScore() {
        return 1;
    }
    @Override
    public String toString () {
        String uniquebuilding = "UniqueBuilding: ";
        uniquebuilding += this.getRules();
        for (Resource[] ra : schematic) {
            for (Resource re : ra) {
                if (re == null) {
                    uniquebuilding += ":empty_tile:";
                } else {
                    uniquebuilding += re.toString();
                }
            }
            uniquebuilding += "\n";
        }
        return uniquebuilding;
    }

    public static void main (String[] Args) {
        Resource[][] schem = new Resource[2][2];
        schem[0][0] = null;
        schem[0][1] = Resource.BRICK;
        schem[1][0] = null;
        schem[1][1] = Resource.GLASS;
        Building ubuild = new UniqueBuilding(schem);
        System.out.println(ubuild);
    }
}