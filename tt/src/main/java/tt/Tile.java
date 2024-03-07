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
                /*can't figure out what to do to get rid of this error, lmk */
                //womp womp i fixed it  -society
            }
        } catch(NullPointerException e) {}
        return null;
    }

    public boolean isEmpty() {
        return object == null;
    }

    public E removeResource() {
        try {
            if (object instanceof Resource) {
                E ret = object;
                object = null;
                return ret;
            }
        } catch(NullPointerException e) {}
        return null;
    }
}