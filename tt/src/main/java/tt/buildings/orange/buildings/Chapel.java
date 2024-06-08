package tt.buildings.orange.buildings;

import tt.*;
import tt.buildings.Cottage;
import tt.buildings.orange.Orange;

public class Chapel extends Orange {

    public Chapel () {
        super("Chapel",new Resource[2][3],0);
        this.getSchematic()[0][0] = Resource.GLASS;
        this.getSchematic()[0][1] = Resource.STONE;
        this.getSchematic()[0][2] = Resource.GLASS;
        this.getSchematic()[1][0] = Resource.STONE;
        this.getSchematic()[1][1] = Resource.EMPTY;
        this.getSchematic()[1][2] = Resource.EMPTY;
    }
    @Override
    public String getRules () {
        return "This building scores 1 point per fed cottage on your board.\n";
    }
    @Override
    public int getScore (Town town, int x, int y) {
        int score = 0;
        for (Tile tile : town) {
            if (tile.getBuildingType() == BuildingType.COTTAGE && ((Cottage)tile.getBuilding()).isFed()) {
                score++;
            }
        }
        return score;
    }

}
