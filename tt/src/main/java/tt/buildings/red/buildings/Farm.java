package tt.buildings.red.buildings;

import tt.*;
import tt.buildings.Cottage;
import tt.buildings.red.Red;

public class Farm extends Red {

    public Farm () {
        super("Farm",new Resource[2][2],0);
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
        int fedCount = 0;
        for (Tile tile : town) {
            if (tile.getBuildingType() == BuildingType.COTTAGE && !((Cottage)tile.getBuilding()).isFed()) {
                ((Cottage)tile.getBuilding()).feed();
                fedCount++;
            }
            if (fedCount >= 4) {
                break;
                
            }
        }
        return 0;
    }
}
