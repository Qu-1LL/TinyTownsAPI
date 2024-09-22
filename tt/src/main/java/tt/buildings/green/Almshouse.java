package tt.buildings.green;

import tt.*;
import tt.buildings.Green;

public class Almshouse extends Green{
    
    private static int[] scores = {0,-1,5,-3,15,-5,26};

    public Almshouse () {
        super("Almshouse",new Resource[1][3],1);
        this.individual = false;
        this.getSchematic()[0][0] = Resource.STONE;
        this.getSchematic()[0][1] = Resource.STONE;
        this.getSchematic()[0][2] = Resource.GLASS;
    }
    @Override
    public String getRules () {
        return "This building scores incrementally per each almshouse in your town," +
        "\n # of almshouses\t:\t1\t2\t3\t4\t5\t6" +
        "\n # of points \t:\t-1\t5\t-3\t15\t-5\t26\n";
    }
    @Override
    public int getScore (Town town, int x, int y) {
        int almshouses = 0;
        for (Tile tile : town) {
            if (tile.getBuildingType() == BuildingType.GREEN) {
                almshouses++;
                if (almshouses >= 6) {
                    break;
                }
            } 
        }

        return scores[almshouses];
    }
}
