package tt;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import tt.buildings.*;

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
        // if (!canBuild(x,y,building)) {
        //     return;
        // }
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

    // v this code will be prospectively removed and replaced with the "Player.java" class v

    // private boolean canBuild (int x, int y, Building building) {
    //     if (town.get(Tile.toCoords(x,y)).getResource() == Resource.EMPTY) {
    //         return false;
    //     }
    //     Resource[][] schematic = building.getSchematic();
    //     Resource[][] newschem = new Resource[schematic.length][schematic[0].length];
    //     for (int i = 1;i < 17;i++) {

    //         newschem = new Resource[schematic[0].length][schematic.length];
    //         for (int j = 0; j < schematic.length; j++) {
    //             for (int k = 0; k < schematic[0].length; k++) {
    //                 newschem[k][schematic.length-1-j] = schematic[j][k];
    //             }
    //         }
    //         schematic = newschem;

    //         if (i % 4 == 0) {
    //             for(int j = 0; j < (schematic.length/2); j++) {
    //                 Resource[] temp = schematic[j];
    //                 schematic[j] = schematic[schematic.length - j - 1];
    //                 schematic[schematic.length - j - 1] = temp;
    //             }
    //         }
    //         if (i % 8 == 0) {
    //             for (int j = 0; j < schematic.length; ++j) {  
    //                 Resource[] col = schematic[j];
    //                 for(int k = 0; k < (col.length/2); k++) {
    //                     Resource temp = col[k];
    //                     col[k] = schematic[j][col.length - k - 1];
    //                     col[col.length - k - 1] = temp;
    //                 }
    //             }
    //         }

    //         if (this.compareSchematic(x,y,schematic)) {
    //             return true;
    //         };
    //     }

    //     return false;
    // }
    // private boolean compareSchematic(int x, int y, Resource[][] schematic) {
    //     boolean ret = true;
    //     for (int xcol = 0;xcol < schematic.length;xcol++) {
    //         for (int xrow = 0; xrow < schematic[0].length;xrow++) {
    //             ret = true;
    //             for (int col = 0;col < schematic.length;col++) {
    //                 for (int row = 0;row < schematic[0].length;row++) {
    //                     try {
    //                         if (!(schematic[col][row] == Resource.EMPTY || schematic[col][row] == town.get(Tile.toCoords(x + row - xrow, y + col - xcol)).getResource())) {
    //                             ret = false;
    //                         }
    //                     } catch (NullPointerException e) {
    //                         return false;
    //                     }
    //                 }
    //             }
    //             if (ret) {
    //                 return ret;
    //             }
    //         }
    //     }
    //     return ret;
    // }

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
    public static void main (String[] Args) {
        Town town = new Town(4);
        town.place(1,1,Resource.STONE);
        town.place(1,2,Resource.WHEAT);
        town.place(2,1,Resource.BRICK);
        town.place(2,2,Resource.GLASS);

        System.out.println(town);

        town.build(1,1,new Cottage());

        System.out.println(town);
    }
}
