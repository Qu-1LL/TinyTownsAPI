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

    /*
     * Start: have everyone who intends to play type "tt join"
     * grab their usernames and create a Player for each
     * add each player to the array
     * 
     * Round Steps: 
     * 
     * 1. Ask that round's builder to type their chosen resource
     * "tt resource"
     * Player's with an empty emptyTiles set will not be builders.
     * 
     * 2. Each player, in no order, must type "tt place x y" to
     * place that round's resource on their board.
     * If a player's emptyTiles set is empty, then they won't 
     * have to do this.
     * 
     * 3. Player's may then build freely by typing 
     * "tt build building x y"
     * If that build isnt possible for some reason then a message
     * will be sent to let them know, but nothing will happen.
     * 
     * 4. Player's can type "tt done" to declare that they are
     * done for that round, and they won't be able to build anymore.
     * Players who's buildableSets were all empty after placing 
     * will automatically be counted as done.
     */


}
