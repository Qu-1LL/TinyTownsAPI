package tt;

import java.lang.NullPointerException;

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
        this.object = null;
    }

    public Holdable getResource() {
        try {
            if (object instanceof Resource) {
                return object;
            }
        } catch(NullPointerException e) {}
        return null;
    }

    public Holdable getBuilding() {
        if (object instanceof Building) {
            return object;
        }
        return null;
    }

    public boolean isEmpty() {
        return object == Resource.EMPTY;
    }

    public Holdable removeResource() {
        if (object instanceof Resource && object != Resource.EMPTY) {
            Holdable ret = object;
            object = Resource.EMPTY;
            return ret;
        }
        return null;
    }
}