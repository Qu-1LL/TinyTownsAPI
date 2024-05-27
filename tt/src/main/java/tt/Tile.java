package tt;

public class Tile {
    private final int x;
    private final int y;
    private Tileable object;
    private Tile north;
    private Tile south;
    private Tile east;
    private Tile west;

    public Tile (int x, int y, Tileable object) {
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
    public Tile[] getAdjacentArray() {
        Tile[] adjacent = new Tile[4];
        adjacent[0] = north;
        adjacent[1] = east;
        adjacent[2] = south;
        adjacent[3] = west;
        return adjacent;
    }
    public Tile[] getSurroundingArray() {
        Tile[] surrounding = new Tile[8];
        surrounding[0] = north;
        surrounding[1] = north.East();
        surrounding[2] = east;
        surrounding[3] = east.South();
        surrounding[4] = south;
        surrounding[5] = south.West();
        surrounding[6] = west;
        surrounding[7] = west.North();
        return surrounding;
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

    public Tileable getTileable() {
        return object;
    }

    public void build(Building building) {
        object = (Tileable)building;
    }

    public boolean isEmpty() {
        return object == Resource.EMPTY;
    }

    public Resource removeResource() {
        if (object instanceof Resource && object != Resource.EMPTY) {
            Tileable ret = object;
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
    public String toString () {
        return object.toPrint();
    }

    @Override
    public boolean equals (Object obj) {
        Tile tile;
        if (obj instanceof Tile) {
            tile = (Tile)obj;
        } else {
            return false;
        }
        if (x == tile.getX() && y == tile.getY() && object.equals(tile.getTileable())) {
            return true;
        } else {
            return false;
        }
    }
}