package tt.buildings;

import tt.*;

public class Farm extends Building {

    public Farm () {
        super("Farm",BuildingType.RED,new Resource[2][2]);
        this.getSchematic()[0][0] = Resource.WHEAT;
        this.getSchematic()[0][1] = Resource.WHEAT;
        this.getSchematic()[1][0] = Resource.WOOD;
        this.getSchematic()[1][1] = Resource.WOOD;
    }
    @Override
    public String getRules() {
        return "This building feeds up to 4 different cottages. " +
        "This building does not score any points on it's own.\n";
    }
    @Override
    public int getScore(Town town, int x, int y) {
        int score = 0;
        for (Tile tile : town) {
            if (tile.getBuildingType() == BuildingType.COTTAGE && !((Cottage)tile.getBuilding()).isFed()) {
                ((Cottage)tile.getBuilding()).feed();
                score += 3;
            }
            if (score == 12) {
                break;
                
            }
        }
        return score;
    }
}
