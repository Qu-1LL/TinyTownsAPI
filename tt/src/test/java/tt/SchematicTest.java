package tt;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;

import tt.buildings.UniqueBuilding;
import tt.buildings.Cottage;

import java.util.HashSet;
import org.junit.Test;

public class SchematicTest {

    public SchematicTest () {
        super();
    }

    @Test
    public void buildableCottageTest () {
        Player player = new Player();
        player.setTown();
        Town town = player.getTown();
        player.setMonument((Building)(new UniqueBuilding(BuildingType.MONUMENT)));
        town.place(2,1,Resource.WHEAT);
        town.place(1,1,Resource.GLASS);
        town.place(1,2,Resource.BRICK);
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
        player.setMonument((Building)(new UniqueBuilding(BuildingType.MONUMENT)));
        town.place(2,1,Resource.WHEAT);
        town.place(1,1,Resource.GLASS);
        town.place(1,2,Resource.BRICK);
        player.updateBoardState();
        player.build(1,2,0);
        HashSet<Tile> emptyTiles = player.getEmptyTiles();
        assertTrue(emptyTiles.contains(town.getTile(1,1)));
        assertTrue(emptyTiles.contains(town.getTile(2,1)));
        assertTrue(town.getTile(1,2).getTileable() instanceof Cottage);
    }

}
