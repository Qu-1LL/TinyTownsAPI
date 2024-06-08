package tt;

import java.util.HashSet;

public class BuildingSet extends HashSet<HashSet<Tile>> {

    public boolean containsTile (Tile tile) {
        for (HashSet<Tile> set : this) {
            if (set.contains(tile)) {
                return true;
            }
        }
        return false;
    }

    public int countTile (Tile tile) {
        int count = 0;
        for (HashSet<Tile> set : this) {
            if (set.contains(tile)) {
                count++;
            }
        }
        return count;
    }

    public HashSet<Tile> grabSet (Tile tile) {
        for (HashSet<Tile> set : this) {
            if (set.contains(tile)) {
                return set;
            }
        }
        return null;
    }

    public HashSet<Tile> buildWith (Tile tile) {
        HashSet<Tile> built = null;
        for (HashSet<Tile> set : this) {
            if (set.contains(tile)) {
                built = set;
                this.remove(set);
            }
        }
        return built;
    }

    public void buildWith (HashSet<Tile> built) {
        this.remove(built);
    }

}
