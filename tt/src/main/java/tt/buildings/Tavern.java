package tt.buildings;

import tt.*;

public class Tavern extends Building {
    private boolean isscored;

    private static int[] scores = {0,2,5,9,14,20};

    public Tavern () {
        super("Tavern",BuildingType.GREEN,new Resource[1][3]);
        this.isscored = false;
        this.getSchematic()[0][0] = Resource.BRICK;
        this.getSchematic()[0][1] = Resource.BRICK;
        this.getSchematic()[0][2] = Resource.GLASS;
    }
    @Override
    public String getRules () {
        return "This building scores incrementally per each tavern in your town," +
        "\n # of taverns  : 1  2  3  4  5" +
        "\n # of points   : 2  5  9  14 20\n";
    }
    public void scored () {
        this.isscored = true;
    }
    @Override
    public int getScore (Town town, int x, int y) {
        int taverns = 0;
        if (!isscored) {
            for (Tile tile : town) {
                if (tile.getBuildingType() == BuildingType.GREEN) {
                    taverns++;
                    if (taverns > 1) {
                        ((Tavern)tile.getBuilding()).scored();
                    }
                    if (taverns >= 5) {
                        break;
                    }
                } 
            }

        }
        return scores[taverns];
    }
}
