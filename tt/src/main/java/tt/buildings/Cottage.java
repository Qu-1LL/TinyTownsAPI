package tt.buildings;

import tt.*;

public class Cottage extends Building {
    private boolean isfed;

    public Cottage () {
        super("Cottage",BuildingType.COTTAGE,new Resource[2][2]);
        this.isfed = false;
        this.getSchematic()[0][0] = Resource.EMPTY;
        this.getSchematic()[0][1] = Resource.WHEAT;
        this.getSchematic()[1][0] = Resource.BRICK;
        this.getSchematic()[1][1] = Resource.GLASS;
    }
    @Override
    public String getRules() {
        return "This building scores 3 points if fed. " +
        "You can feed a cottage with a red building in your town.\n";
    }
    @Override
    public int getScore(Town town, int x, int y) {
        if (isfed) {
            return 3;
        }
        else {
            return 0;
        }
    }
    public boolean isFed () {
        return isfed;
    }
    public void feed() {
        isfed = true;
    }
    @Override
    public Cottage build () {
        return new Cottage();
    }
}
