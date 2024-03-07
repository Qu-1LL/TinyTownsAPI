package tt;

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
    public Tile getNorth() {
        return north;
    }
    public Tile getSouth() {
        return south;
    }
    public Tile getEast() {
        return east;
    }
    public Tile getWest() {
        return west;
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
}