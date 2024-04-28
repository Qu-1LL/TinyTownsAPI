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
            "in the same row and columns as this building.";
    }
    @Override
    public int getScore (Town town, int x, int y) {
        int score = 0;
        HashSet<BuildingType> set = new HashSet<>();
        Tile curtile = town.getTile(x,y);
        for (int i = 0; i < 4; i++) {
            while (curtile != null) {
                if (curtile != town.getTile(x,y) && curtile.getBuildingType() != null) {
                    set.add(curtile.getBuildingType());
                }
                if (i == 0) {
                    curtile = curtile.North();
                } else if (i == 1) {
                    curtile = curtile.East();
                } else if (i == 2) {
                    curtile = curtile.South();
                } else if (i == 3) {
                    curtile = curtile.West();
                }
                // does not work
            }
            curtile = town.getTile(x,y);
        }
        score = set.size();
        return score;
    }
}

/*
Try implementing a version of this code in the Tile.java class
Idea is to make the directions iterable
Iterating through a series of methods is difficult tho

interface MoveAction {
    void move();
}

private MoveAction[] moveActions = new MoveAction[] {
    new MoveAction() { public void move() { goNorth(); } },
    new MoveAction() { public void move() { goSouth(); } },
    new MoveAction() { public void move() { goEast(); } },
    new MoveAction() { public void move() { goWest(); } },
};

public void move(int index) {
    moveActions[index].move();
}
*/
