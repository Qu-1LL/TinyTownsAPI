package tt;

import java.util.Iterator;

public class TownIterator implements Iterator<Tile>{
    private int index;
    private Town town;

    public TownIterator (Town town) {
        this.town = town;
        this.index = 0;
    }

    @Override
    public Tile next () {
        int currindex = index;
        index++;
        return town.getTile(currindex % town.size(),(int)Math.floor(currindex / 4));
    }
    @Override
    public boolean hasNext () {
        return index < Math.pow(town.size(),2);
    }
}
