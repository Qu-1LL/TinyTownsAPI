package tt;

import tt.buildings.*;
import tt.buildings.monument.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;

public class Game {

    private TT tt;

    private int playerCount;
    private Player[] playersArray;
    private HashSet<Player> playersSet;

    public Cottage cottage;
    public Yellow yellow;
    public Orange orange;
    public Red red;
    public Green green;
    public Navy navy;
    public Grey grey;

    private Resource roundResource;


    public Game (Cottage cottage, Yellow yellow, Orange orange, Red red, Green green, Navy navy, Grey grey) {
        this.cottage = cottage;
        this.yellow = yellow;
        this.orange = orange;
        this.red = red;
        this.green = green;
        this.navy = navy;
        this.grey = grey;
        this.playersSet = new HashSet<Player>();
    }
    
    private boolean makePlayers (Collection<String> usernames) {
        this.playerCount = usernames.size();
        this.playersArray = new Player[playerCount];
        int count = 0;
        for (String username : usernames) {
            playersArray[count] = new Player(username,this);
            // Remove this next line of code once monument system is properly set up.
            playersArray[count].setMonument((Monument)new MandrasPalace());
            playersSet.add(playersArray[count]);
            count++;
        }
        return playerCount > 0;
    }
    
    private boolean placeResource (String username, int x, int y) {
        // User input will be 1-4, data is stored 0-3
        for (Player player : playersArray) {
            if (player.getName() == username) {
                player.place(roundResource,x,y);
                return true;
            }
        }
        return false;
    }

    private void updateActive (HashSet<Player> activePlayers, HashSet<Player> finishedPlayers) {
        for (Player player : activePlayers) {
            int count = 0;
            for (BuildingSet set : player.getBuildableSets()) {
                if (set.size() == 0 && player.getEmptyTiles().size() == 0) {
                    continue;
                } else {
                    count++;
                }
            }
            if (count == 8) {
                activePlayers.remove(player);
                finishedPlayers.add(player);
            }
        }
    }

    public void start () {
        if (!makePlayers(tt.getUsernames())) {
            return;
        }
        HashSet<Player> activePlayers = new HashSet<>(Set.of(playersArray));
        HashSet<Player> finishedPlayers = new HashSet<>();
        while (activePlayers.size() > 0) {
            for (Player player : playersArray) {
                if (activePlayers.contains(player)) {
                    this.roundResource = tt.getRoundResource(player.getName());
                    HashMap<String,Player> playersMap = new HashMap<>();
                    for (Player p : activePlayers) {
                        playersMap.put(p.getName(),p);
                    }
                    tt.placeResources(playersMap);
                    updateActive(activePlayers,finishedPlayers);
                    if (tt.freeBuild(activePlayers)) {
                        updateActive(activePlayers,finishedPlayers);
                    }
                }
            }
        }
        tt.gameEnd();
    }

    public Player[] getPlayersArray() {
        return playersArray;
    }

    public TT getTT() {
        return tt;
    }

    /*
     * Start: have everyone who intends to play type "tt join"
     * grab their usernames and create a Player for each
     * add each player to the array
     *
     * Round Steps: 
     * 
     * 1. Ask that round's builder to select their resource
     * (Try to do this with a button.)
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
     * If a player's emptyTiles set is empty, but any of their 
     * buildableSets aren't, they will be forced to build.
     * 
     * 4. Player's can press a "done" button to declare that they are
     * done for that round, and they won't be able to build anymore.
     * Players who's buildableSets were all empty after placing 
     * will automatically be counted as done.
     */

     // Write gameloop, use missing functions to create interface for future implementations


}
