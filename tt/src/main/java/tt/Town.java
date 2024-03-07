package tt;

import java.util.HashMap;
import java.util.Map;

public class Town {
    private Map<Coordinates,Tile> town;

    public Town () {
        town = new HashMap<>();
    }
    public void add (Tile tile) {
        town.put(tile.getCoords(),tile);
    }
    public boolean contains (Tile tile) {
        return town.containsKey (tile.getCoords());
    }
    public Tile getTile (Coordinates coords) {
        return town.get(coords);
    }
    public int size () {
        return town.size();
    }
    public static void connect(Tile a, Tile b) {
        if (a.getX() == b.getX()) {
            if (a.getY() > b.getY()) {
                a.setSouth(b);
                b.setNorth(a);
            }
            if (a.getY() < b.getY()) {
                a.setNorth(b);
                b.setSouth(a);
            }
        }
        if (a.getY() == b.getY()) {
            if (a.getX() > b.getX()) {
                a.setWest(b);
                b.setEast(a);
            }
            if (a.getX() < b.getX()) {
                a.setEast(b);
                b.setWest(a);
            }
        }
    }
    public boolean connected(Tile a, Tile b) {
        return a.North() == b || a.East() == b || a.South() == b || a.West() == b;
    }
    @Override 
    public String toString () {
        String tstring = "";
        Tile curtile = town.get(new Coordinates(0,0));
        Tile oldtile = curtile;
        while (true) {
            tstring += curtile.toString();
            curtile = curtile.East();
            if (curtile == null) {
                tstring += "\n";
                curtile = oldtile.South();
                oldtile = curtile;
            }
            if (curtile == null) {
                break;
            }
        }
        return tstring;
    }
    public static void main (String[] Args) {
        
    }
}
