package tt.buildings.green;

import tt.*;
import tt.buildings.Green;

public class Tavern extends Green {

    private static int[] scores = {0,2,5,9,14,20};

    public Tavern () {
        super("Tavern",new Resource[1][3],0);
        this.individual = false;
        this.getSchematic()[0][0] = Resource.BRICK;
        this.getSchematic()[0][1] = Resource.BRICK;
        this.getSchematic()[0][2] = Resource.GLASS;
    }
    @Override
    public String getRules () {
        return "This building scores incrementally per each tavern in your town," +
        "\n # of taverns\t:\t1\t2\t3\t4\t5" +
        "\n # of points \t:\t2\t5\t9\t14\t20\n";
    }
    @Override
    public int getScore (Town town, int x, int y) {
        int taverns = 0;
        for (Tile tile : town) {
            if (tile.getBuildingType() == BuildingType.GREEN) {
                taverns++;
                if (taverns >= 5) {
                    break;
                }
            } 
        }

        return scores[taverns];
    }
}
