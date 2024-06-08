package tt.buildings.yellow;

import tt.*;
import tt.buildings.Yellow;

import java.util.HashSet;

public class Theatre extends Yellow {

    public Theatre () {
        super("Theatre",new Resource[2][3],0);
        this.getSchematic()[0][0] = Resource.EMPTY;
        this.getSchematic()[0][1] = Resource.STONE;
        this.getSchematic()[0][2] = Resource.EMPTY;
        this.getSchematic()[1][0] = Resource.WOOD;
        this.getSchematic()[1][1] = Resource.GLASS;
        this.getSchematic()[1][2] = Resource.WOOD;
    }
    @Override
    public String getRules () {
        return "This building scores 1 point for each unique building " +
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