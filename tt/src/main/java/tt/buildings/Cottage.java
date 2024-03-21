package tt.buildings;

import tt.*;

public class Cottage extends Building implements Holdable{
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
        return "This building scores 3 points if fed.\n" +
        "you can feed a cottage with a red building in your town.\n";
    }
    public boolean isFed () {
        return isfed;
    }
    public void feed() {
        isfed = true;
    }

    public static void main (String[] Args) {
        Cottage cottage = new Cottage();
        System.out.println(cottage);
    }
}
