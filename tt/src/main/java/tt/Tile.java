package tt;

import java.lang.NullPointerException;

public class Tile<E> {
    public final int x;
    public final int y;
    public E object;


    public Tile (int x, int y, E object) {
        this.x = x;
        this.y = y;
        this.object = object;
    }
    public Tile (int x, int y) {
        this.x = x;
        this.y = y;
        this.object = null;
    }

    public E getResource() {
        try {
            if (object instanceof Resource) {
                return object;
            }
        } catch(NullPointerException e) {}
        return null;
    }

    public BuildingType getBuilding() {
        try {
            if (object instanceof Building) {
                Building otherBuilding = (Building) object;
                return otherBuilding.getType();
            }
        } catch(NullPointerException e) {}
        return null;
    }

    public boolean isEmpty() {
        return object == Resource.EMPTY;
    }

    public E removeResource() {
        try {
            if (object instanceof Resource) {
                E ret = object;
                try {
                    object = Resource.EMPTY;
                } finally {}
                return ret;
            }
        } catch(NullPointerException e) {}
        return null;
    }
}