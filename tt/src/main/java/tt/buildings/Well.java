package tt.buildings;

import tt.*;

public class Well extends Building {

    public Well () {
        super("Well",BuildingType.GREY,new Resource[1][2]);
        this.getSchematic()[0][0] = Resource.STONE;
        this.getSchematic()[0][1] = Resource.WOOD;
    }
    @Override
    public String getRules () {
        return "This buuilding scores 1 point per cottage adjacent to it.\n";
    }
    @Override
    public int getScore(Town town, int x, int y) {
        int score = 0;
        Tile curtile = town.getTile(x,y);
        for (Tile tile : curtile.getAdjacentArray()) {
            if (tile.getBuildingType() == BuildingType.COTTAGE) {
                score++;
            }
        }
        return score;
    }

}
