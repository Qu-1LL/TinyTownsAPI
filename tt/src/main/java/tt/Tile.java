package tt;

import java.util.Set;
import java.util.HashSet;

public class Tile {
    private final int x;
    private final int y;
    private Holdable object;
    private Tile north;
    private Tile south;
    private Tile east;
    private Tile west;


    public Tile (int x, int y, Holdable object) {
        this.x = x;
        this.y = y;
        this.object = object;
    }
    public Tile (int x, int y) {
        this.x = x;
        this.y = y;
        this.object = Resource.EMPTY;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getCoords() {
        return ((Integer)x).toString() + " " + ((Integer)y).toString();
    }
    public static String toCoords(int x, int y) {
        return ((Integer)x).toString() + " " + ((Integer)y).toString();
    }
    public Tile North() {
        return north;
    }
    public void setNorth(Tile north) {
        this.north = north;
    }
    public Tile South() {
        return south;
    }
    public void setSouth(Tile south) {
        this.south = south;
    }
    public Tile East() {
        return east;
    }
    public void setEast(Tile east) {
        this.east = east;
    }
    public Tile West() {
        return west;
    }
    public void setWest(Tile west) {
        this.west = west;
    }
    public Set<Tile> getAdjacent() {
        Set<Tile> adjacent = new HashSet<>();
        adjacent.add(north);
        adjacent.add(south);
        adjacent.add(east);
        adjacent.add(west);
        return adjacent;
    }

    public Resource getResource() {
        if (object instanceof Resource) {
            return (Resource)object;
        }
        return null;
    }

    public Building getBuilding() {
        if (object instanceof Building) {
            return (Building)object;
        }
        return null;
    }

    public BuildingType getBuildingType() {
        if (object instanceof Building) {
            Building ret = (Building)object;
            return ret.getType();
        }
        return null;
    }

    public void build(Holdable building) {
        object = building;
    }

    public boolean isEmpty() {
        return object == Resource.EMPTY;
    }

    public Resource removeResource() {
        if (object instanceof Resource && object != Resource.EMPTY) {
            Holdable ret = object;
            object = Resource.EMPTY;
            return (Resource)ret;
        }
        return null;
    }

    public void setResource(Resource resource) {
        if (object instanceof Resource) {
            if (object == Resource.EMPTY) {
                object = resource;
            }
        }
    }

    public int getScore(Town town) {
        Building building = this.getBuilding();
        if (building == null) {
            return -1;
        } else {
            return building.getScore(town, x, y);
        }

    }

    @Override
    public String toString() {
        if (object instanceof Resource) {
            return object.toString();
        } else {
            if (object instanceof Building) {
                return this.getBuildingType().toString();
            }
        }
        return null;
    }
}