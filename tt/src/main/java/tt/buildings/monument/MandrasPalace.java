package tt.buildings.monument;

import tt.*;
import tt.buildings.Monument;

import java.util.HashSet;

public class MandrasPalace extends Monument {

    public MandrasPalace () {
        super("Mandras Palace",new Resource[2][2],0);
        this.getSchematic()[0][0] = Resource.WHEAT;
        this.getSchematic()[0][1] = Resource.GLASS;
        this.getSchematic()[1][0] = Resource.BRICK;
        this.getSchematic()[1][1] = Resource.WOOD;
    }
    @Override
    public String getRules () {
        return "This building scores 2 points for each unique adjacent building.";
    }
    @Override
    public int getScore (Town town, int x, int y) {
        int score = 0;
        HashSet<BuildingType> unique = new HashSet<>();
        for (Tile tile : town.getTile(x,y).getAdjacentArray()) {
            if (tile != null) {
                unique.add(tile.getBuildingType());
            }
        }
        score = unique.size();
        return score;
    }
}
