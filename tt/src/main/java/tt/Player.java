package tt;

import java.util.HashSet;
import java.util.ArrayList;

public class Player {

    public String username;
    private Town town;
    private HashSet<Tile> emptyTiles;
    private HashSet<Tile> buildableCottage;
    private HashSet<Tile> buildableYellow;
    private HashSet<Tile> buildableOrange;
    private HashSet<Tile> buildableRed;
    private HashSet<Tile> buildableGreen;
    private HashSet<Tile> buildableNavy;
    private HashSet<Tile> buildableGrey;
    private HashSet<Tile> buildableMonument;
    private ArrayList<HashSet<Tile>> buildableSets;

    public Player (String username) {
        this.username = username;

        this.town = new Town(4);
        this.emptyTiles = new HashSet<Tile>();
        for (Tile tile : town) {
            emptyTiles.add(tile);
        }

        this.buildableCottage = new HashSet<Tile>();
        this.buildableYellow = new HashSet<Tile>();
        this.buildableOrange = new HashSet<Tile>();
        this.buildableRed = new HashSet<Tile>();
        this.buildableGreen = new HashSet<Tile>();
        this.buildableNavy = new HashSet<Tile>();
        this.buildableGrey = new HashSet<Tile>();
        this.buildableMonument = new HashSet<Tile>();

        this.buildableSets = new ArrayList<HashSet<Tile>>();
        buildableSets.add(buildableCottage);
        buildableSets.add(buildableYellow);
        buildableSets.add(buildableOrange);
        buildableSets.add(buildableRed);
        buildableSets.add(buildableGreen);
        buildableSets.add(buildableNavy);
        buildableSets.add(buildableGrey);
        buildableSets.add(buildableMonument);
    }

    public Town getTown () {
        return town;
    }

}
