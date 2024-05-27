package tt.buildings;

import tt.*;
import java.util.HashSet;

public class Theatre extends Building {

    public Theatre () {
        super("Theatre",BuildingType.YELLOW,new Resource[2][3]);
        this.getSchematic()[0][0] = Resource.EMPTY;
        this.getSchematic()[0][1] = Resource.STONE;
        this.getSchematic()[0][2] = Resource.EMPTY;
        this.getSchematic()[1][0] = Resource.WOOD;
        this.getSchematic()[1][1] = Resource.GLASS;
        this.getSchematic()[1][2] = Resource.WOOD;
    }
    @Override
    public String getRules () {
        return "This building scores 1 point for each unique building\n" +
            "in the same row and column as this building.\n";
    }
    @Override
    public int getScore (Town town, int x, int y) {
        int score = 0;
        HashSet<BuildingType> set = new HashSet<>();
        Tile curtile = town.getTile(x,y);
        for (int i = 0; i < 4; i++) {
            while (curtile != null) {
                if (!curtile.equals(town.getTile(x,y)) && curtile.getBuildingType() != null) {
                    set.add(curtile.getBuildingType());
                }
                curtile = curtile.getAdjacentArray()[i];
            }
            curtile = town.getTile(x,y);
        }
        score = set.size();
        return score;
    }
}