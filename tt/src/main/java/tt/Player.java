package tt;

import java.util.HashSet;
import java.util.ArrayList;
import tt.buildings.*;
import tt.buildings.green.Tavern;
import tt.buildings.grey.Well;
import tt.buildings.navy.Factory;
import tt.buildings.orange.Chapel;
import tt.buildings.red.Farm;
import tt.buildings.yellow.Theatre;

public class Player {

    public String username;
    private Town town;
    private Game game;
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

    private Building monument;

    public Player (String username, Game game) {
        this.username = username;
        this.game = game;

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

    public void updateBoardState () {
        emptyTiles = findEmpty();
        buildableCottage = findBuildable(game.cottage.getSchematic());
        buildableYellow = findBuildable(game.yellow.getSchematic());
        buildableOrange = findBuildable(game.orange.getSchematic());
        buildableRed = findBuildable(game.red.getSchematic());
        buildableGreen = findBuildable(game.green.getSchematic());
        buildableNavy = findBuildable(game.navy.getSchematic());
        buildableGrey = findBuildable(game.grey.getSchematic());
        buildableMonument = findBuildable(monument.getSchematic());
    }

    private HashSet<Tile> findEmpty () {
        HashSet<Tile> empty = new HashSet<>();
        for (Tile tile : town) {
            if (tile.getResource() == Resource.EMPTY) {
                empty.add(tile);
            }
        }
        return empty;
    }

    private HashSet<Tile> findBuildable (Resource[][] schem) {
        Resource[][] schematic = schem;
        HashSet<Tile> buildable = new HashSet<Tile>();
        for (int i = 1; i < 17;i++) {
            schematic = rotateCW(schematic);
            if (i % 4 == 0) {
                mirrorH(schematic);
            }
            if (i % 8 == 0) {
                mirrorV(schematic);
            }
            compareSchematic(schematic,buildable);
        }
        return buildable;
    }

    static Resource[][] rotateCW(Resource[][] schematic) {
        final int M = schematic.length;
        final int N = schematic[0].length;
        Resource[][] ret = new Resource[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M-1-r] = schematic[r][c];
            }
        }
        return ret;
    }

    static void mirrorH(Resource[][] schematic) {
        for(int j = 0; j < (schematic.length/2); j++) {
            Resource[] temp = schematic[j];
            schematic[j] = schematic[schematic.length - j - 1];
            schematic[schematic.length - j - 1] = temp;
        }
    }

    static void mirrorV(Resource[][] schematic) {
        for (int j = 0; j < schematic.length; ++j) {  
            Resource[] col = schematic[j];
            for(int k = 0; k < (col.length/2); k++) {
                Resource temp = col[k];
                col[k] = schematic[j][col.length - k - 1];
                col[col.length - k - 1] = temp;
            }
        }
    }

    private HashSet<Tile> compareSchematic (Resource[][] schematic, HashSet<Tile> buildable) {
        boolean fail = false;
        for (int i = 0;i <= (4-schematic[0].length);i++) {
            for (int j = 0;j <= (4-schematic.length);j++) {
                fail = false;
                for (int x = 0; x < schematic[0].length;x++) {
                    for (int y = 0; y < schematic.length;y++) {
                        if (town.getTile(x+i,y+j).getResource() != schematic[y][x] && schematic[y][x] != Resource.EMPTY) {
                            fail = true;
                            break;
                        }
                    }
                    if (fail) {break;}
                }
                if (fail) {continue;}
                fillBuildable(i,j,schematic,buildable);
            }
        }

        return buildable;
    }

    private void fillBuildable (int i, int j, Resource[][] schematic, HashSet<Tile> buildable) {
        for (int x = 0; x < schematic[0].length;x++) {
            for (int y = 0; y < schematic.length;y++) {
                if (schematic[y][x] == Resource.EMPTY) {
                    continue;
                } else {
                    buildable.add(town.getTile(x+i,y+j));
                }
            }
        }
    }

    public Building getMonument() {
        return monument;
    }
    public void setMonument(Building monument) {
        this.monument = monument;
    }

    public HashSet<Tile> getBuildableCottage () {
        return buildableCottage;
    }
    public HashSet<Tile> getBuildableYellow () {
        return buildableYellow;
    }
    public HashSet<Tile> getBuildableOrange () {
        return buildableOrange;
    }
    public HashSet<Tile> getBuildableRed () {
        return buildableRed;
    }
    public HashSet<Tile> getBuildableGreen () {
        return buildableGreen;
    }
    public HashSet<Tile> getBuildableNavy () {
        return buildableNavy;
    }
    public HashSet<Tile> getBuildableGrey () {
        return buildableGrey;
    }
    public HashSet<Tile> getBuildableMonument () {
        return buildableMonument;
    }
    public HashSet<Tile> getEmptyTiles () {
        return emptyTiles;
    }

    // Everything below is to be used for testing only

    protected Player () {
        this.username = "tester";
        this.game = new Game(new Cottage(),new Theatre(),new Chapel(),new Farm(),new Tavern(),new Factory(),new Well());

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

    protected void setTown () {
        this.town = new Town(4);
    }

}
