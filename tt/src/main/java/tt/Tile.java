package tt;

public class Tile {
    public final int x;
    public final int y;
    public Holdable object;


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