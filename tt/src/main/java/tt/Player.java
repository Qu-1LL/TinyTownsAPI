package tt;

import java.util.HashSet;

import tt.buildings.*;
import tt.buildings.green.*;
import tt.buildings.grey.*;
import tt.buildings.navy.*;
import tt.buildings.orange.*;
import tt.buildings.red.*;
import tt.buildings.yellow.*;

public class Player {

    public String username;
    private Town town;
    private Game game;
    private HashSet<Tile> emptyTiles;
    private BuildingSet buildableCottage;
    private BuildingSet buildableYellow;
    private BuildingSet buildableOrange;
    private BuildingSet buildableRed;
    private BuildingSet buildableGreen;
    private BuildingSet buildableNavy;
    private BuildingSet buildableGrey;
    private BuildingSet buildableMonument;
    private BuildingSet[] buildableSets;

    private Building[] buildingsTypes;

    private Building monument;

    public Player (String username, Game game) {
        this.username = username;
        this.game = game;

        buildingsTypes = new Building[8];
        buildingsTypes[0] = game.cottage;
        buildingsTypes[1] = game.yellow;
        buildingsTypes[2] = game.orange;
        buildingsTypes[3] = game.red;
        buildingsTypes[4] = game.green;
        buildingsTypes[5] = game.navy;
        buildingsTypes[6] = game.grey;
        buildingsTypes[7] = this.monument;

        this.town = new Town(4);
        this.emptyTiles = new HashSet<Tile>();
        for (Tile tile : town) {
            emptyTiles.add(tile);
        }

        this.buildableCottage = new BuildingSet();
        this.buildableYellow = new BuildingSet();
        this.buildableOrange = new BuildingSet();
        this.buildableRed = new BuildingSet();
        this.buildableGreen = new BuildingSet();
        this.buildableNavy = new BuildingSet();
        this.buildableGrey = new BuildingSet();
        this.buildableMonument = new BuildingSet();

        buildableSets = new BuildingSet[8];
        buildableSets[0] = buildableCottage;
        buildableSets[1] = buildableYellow;
        buildableSets[2] = buildableOrange;
        buildableSets[3] = buildableRed;
        buildableSets[4] = buildableGreen;
        buildableSets[5] = buildableNavy;
        buildableSets[6] = buildableGrey;
        buildableSets[7] = buildableMonument;
    }

    public String getName() {
        return username;
    }

    public boolean build (int x, int y, int index) {
        BuildingSet set = buildableSets[index];
        Tile tile = town.getTile(x,y);

        if (!set.containsTile(tile)) {
            return false;
        }
        if (set.countTile(tile) > 1) {
            // put code here to deal with picking from the multiple schematics
        } else {
            for (Tile t : set.buildWith(tile)) {
                t.removeResource();
            }
        }
        town.build(x,y,buildingsTypes[index]);
        updateBoardState();
        return true;
    }

    public void place (Resource resource, int x, int y) {
        town.place(resource,x,y);
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
        buildableSets[0] = buildableCottage;
        buildableSets[1] = buildableYellow;
        buildableSets[2] = buildableOrange;
        buildableSets[3] = buildableRed;
        buildableSets[4] = buildableGreen;
        buildableSets[5] = buildableNavy;
        buildableSets[6] = buildableGrey;
        buildableSets[7] = buildableMonument;
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

    private BuildingSet findBuildable (Resource[][] schem) {
        Resource[][] schematic = schem;
        BuildingSet buildables = new BuildingSet();
        for (int i = 1; i < 17;i++) {
            schematic = rotateCW(schematic);
            if (i % 4 == 0) {
                mirrorH(schematic);
            }
            if (i % 8 == 0) {
                mirrorV(schematic);
            }
            compareSchematic(schematic,buildables);
        }
        return buildables;
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

    private BuildingSet compareSchematic (Resource[][] schematic, BuildingSet buildables) {
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
                HashSet<Tile> buildable = new HashSet<Tile>();
                fillBuildable(i,j,schematic,buildable);
                buildables.add(buildable);
            }
        }

        return buildables;
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

    // Everything below this is probably only for testing

    public BuildingSet getBuildableCottages () {
        return buildableCottage;
    }
    public BuildingSet getBuildableYellows () {
        return buildableYellow;
    }
    public BuildingSet getBuildableOranges () {
        return buildableOrange;
    }
    public BuildingSet getBuildableReds () {
        return buildableRed;
    }
    public BuildingSet getBuildableGreens () {
        return buildableGreen;
    }
    public BuildingSet getBuildableNavys () {
        return buildableNavy;
    }
    public BuildingSet getBuildableGreys () {
        return buildableGrey;
    }
    public BuildingSet getBuildableMonuments () {
        return buildableMonument;
    }
    public BuildingSet[] getBuildableSets () {
        return buildableSets;
    }
    public HashSet<Tile> getEmptyTiles () {
        return emptyTiles;
    }

    // Everything below is to be used for testing only

    protected Player () {
        this("tester",new Game(new Cottage(),new Theatre(),new Chapel(),new Farm(),new Tavern(),new Factory(),new Well()));
    }

    protected void setTown () {
        this.town = new Town(4);
    }

}
