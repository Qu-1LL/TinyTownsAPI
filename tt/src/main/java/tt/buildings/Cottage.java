package tt.buildings;

import tt.*;

public class Cottage extends Building implements Holdable{

    public Cottage () {
        super("Cottage",BuildingType.COTTAGE,new Resource[2][2]);
        this.getSchematic()[0][0] = Resource.EMPTY;
        this.getSchematic()[0][1] = Resource.WHEAT;
        this.getSchematic()[1][0] = Resource.BRICK;
        this.getSchematic()[1][1] = Resource.GLASS;
    }
    @Override
    public Holdable getHeld() {
        return this;
    }
    @Override
    public String getRules() {
        return "This building scores 3 points if fed.\n" +
        "you can feed a cottage with a red building in your town.\n";
    }
    @Override
    public int getScore(Town town, int x, int y) {
        return 0;
    }

    public static void main (String[] Args) {
        Cottage cottage = new Cottage();
        System.out.println(cottage);
    }
}
