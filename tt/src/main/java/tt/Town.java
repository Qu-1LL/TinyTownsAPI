package tt;

import java.util.HashMap;
import java.util.Map;
import tt.buildings.*;

public class Town {
    private Map<String,Tile> town;
    private int size;

    public Town (int size) {
        town = new HashMap<String,Tile>();
        this.size = size;
        this.fillTown();
    }
    public void add (Tile tile) {
        town.put(tile.getCoords(),tile);
    }
    public boolean contains (Tile tile) {
        return town.containsKey(tile.getCoords());
    }
    public Tile getTile (int x, int y) {
        return town.get(toCoords(x,y));
    }
    public int size () {
        return size;
    }
    public static String toCoords(int x, int y) {
        return x + " " + y;
    }
    public void build (int x, int y) {
        Tile tobuild = town.get(toCoords(x,y));
        tobuild.build(new UniqueBuilding(BuildingType.COTTAGE));
    }
    public int score() {
        int score = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                score += town.get(toCoords(j,i)).getScore(this);
            }
        }
        return score;
    }
    public static void connect(Tile a, Tile b) {
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
    private void fillTown () {
        Tile curtile;
        for (int i = 0;i < size;i++) {
            for (int j = 0;j < size; j++) {
                curtile = new Tile(j,i);
                town.put(curtile.getCoords(),curtile);
                if (j != 0) {
                    curtile.setWest(town.get(toCoords(j-1,i)));
                }
                if (i != 0) {
                    curtile.setNorth(town.get(toCoords(j,i-1)));
                }
            }
        }
    }
    @Override 
    public String toString () {
        String tstring = "";
        Tile curtile = town.get(toCoords(0,0));
        for (int i = 0;i < size;i++) {
            for (int j = 0;j < size; j++) {
                curtile = town.get(toCoords(j,i));
                tstring += curtile.toString();
            }
            tstring += "\n";
        }
        return tstring;
    }
    public static void main (String[] Args) {
        Town town = new Town(4);
        System.out.println(town);
    }
}
