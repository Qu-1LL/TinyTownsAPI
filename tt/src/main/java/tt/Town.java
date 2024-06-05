package tt;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Town implements Iterable<Tile>{
    private Map<String,Tile> town;
    private int size;

    public Town (int size) {
        town = new HashMap<String,Tile>();
        this.size = size;
        this.fillTown();
    }
    public Tile getTile (int x, int y) {
        return town.get(Tile.toCoords(x,y));
    }
    public int size () {
        return size;
    }
    public void place (int x, int y, Resource resource) {
        if (town.get(Tile.toCoords(x,y)).getResource() == Resource.EMPTY && town.get(Tile.toCoords(x,y)).getBuilding() == null) {
            town.get(Tile.toCoords(x,y)).setResource(resource);
        }
    }
    public void build (int x, int y, Building building) {
        town.get(Tile.toCoords(x,y)).build(building);
    }
    public int score() {
        int score = 0;
        for (Tile tile : this) {
            if (tile.getBuildingType() == BuildingType.RED) {
                score += town.get(tile.getCoords()).getScore(this);
            }
        }
        for (Tile tile : this) {
            score += town.get(tile.getCoords()).getScore(this);
        }
        return score;
    }
    // Used in fillTown
    private static void connect(Tile a, Tile b) {
        if (a.getX() == b.getX()) {
            if (a.getY()-1 == b.getY()) {
                a.setSouth(b);
                b.setNorth(a);
            }
            if (a.getY()+1 == b.getY()) {
                a.setNorth(b);
                b.setSouth(a);
            }
        }
        if (a.getY() == b.getY()) {
            if (a.getX()-1 == b.getX()) {
                a.setWest(b);
                b.setEast(a);
            }
            if (a.getX()+1 == b.getX()) {
                a.setEast(b);
                b.setWest(a);
            }
        }
    }
    public boolean connected(Tile a, Tile b) {
        return a.North() == b || a.East() == b || a.South() == b || a.West() == b;
    }
    // Used in constructor
    private void fillTown () {
        Tile curtile;
        for (int i = 0;i < size;i++) {
            for (int j = 0;j < size; j++) {
                curtile = new Tile(j,i);
                town.put(curtile.getCoords(),curtile);

                if (j != 0) {
                    connect(curtile,town.get(Tile.toCoords(j-1,i)));
                    if (j == size - 1) {
                        curtile.setEast(null);
                    }
                } else {
                    curtile.setWest(null);
                }
            
                if (i != 0) {
                    connect(curtile,town.get(Tile.toCoords(j,i-1)));
                    if (i == size - 1) {
                        curtile.setSouth(null);
                    }
                } else { 
                    curtile.setNorth(null);
                }   
            }
        }
    }

    @Override
    public Iterator<Tile> iterator() {
        return new TownIterator(this);
    }
    @Override
    public String toString () {
        String tstring = "";
        int i = 1;
        for (Tile tile : this) {
            tstring += tile.toString();
            if (i % size == 0) {
                tstring += "\n";
            }
            i++;
        }
        return tstring;
    }
}
