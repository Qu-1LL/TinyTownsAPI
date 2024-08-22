package tt;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;

import tt.buildings.monument.*;
import tt.buildings.Cottage;
import tt.buildings.Monument;

import java.util.HashSet;
import org.junit.Test;

public class SystemTest {

    public SystemTest () {
        super();
    }

    @Test
    public void buildableCottageTest () {
        Player player = new Player();
        player.setTown();
        Town town = player.getTown();
        player.setMonument((Monument)(new MandrasPalace()));
        town.place(Resource.WHEAT,2,1);
        town.place(Resource.GLASS,1,1);
        town.place(Resource.BRICK,1,2);
        player.updateBoardState();
        BuildingSet buildableCottage = player.getBuildableCottages();

        assertEquals(buildableCottage.size(),1);
        assertTrue(buildableCottage.containsTile(town.getTile(2,1)));
        assertTrue(buildableCottage.containsTile(town.getTile(1,1)));
        assertTrue(buildableCottage.containsTile(town.getTile(1,2)));
    }
    @Test
    public void buildCottageTest () {
        Player player = new Player();
        player.setTown();
        Town town = player.getTown();
        player.setMonument((Monument)(new MandrasPalace()));
        town.place(Resource.WHEAT,2,1);
        town.place(Resource.GLASS,1,1);
        town.place(Resource.BRICK,1,2);
        player.updateBoardState();
        player.build(1,2,0);
        HashSet<Tile> emptyTiles = player.getEmptyTiles();
        
        assertTrue(emptyTiles.contains(town.getTile(1,1)));
        assertTrue(emptyTiles.contains(town.getTile(2,1)));
        assertTrue(town.getTile(1,2).getTileable() instanceof Cottage);
    }

}
