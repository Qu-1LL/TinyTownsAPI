package tt;

import tt.buildings.*;

public class Game {

    private int playerCount;
    private Player[] players;

    public Building cottage;
    public Building yellow;
    public Building orange;
    public Building red;
    public Building green;
    public Building navy;
    public Building grey;


    public Game (Building cottage, Building yellow, Building orange, Building red, Building green, Building navy, Building grey) {
        this.cottage = cottage;
        this.yellow = yellow;
        this.orange = orange;
        this.red = red;
        this.green = green;
        this.navy = navy;
        this.grey = grey;
    }


}
