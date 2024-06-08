package tt;

import tt.buildings.*;

public class Game {

    private int playerCount;
    private Player[] players;

    public Cottage cottage;
    public Yellow yellow;
    public Orange orange;
    public Red red;
    public Green green;
    public Navy navy;
    public Grey grey;


    public Game (Cottage cottage, Yellow yellow, Orange orange, Red red, Green green, Navy navy, Grey grey) {
        this.cottage = cottage;
        this.yellow = yellow;
        this.orange = orange;
        this.red = red;
        this.green = green;
        this.navy = navy;
        this.grey = grey;
    }


}
