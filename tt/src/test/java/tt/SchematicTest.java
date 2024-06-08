package tt;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import tt.buildings.UniqueBuilding;

import java.util.HashSet;

import org.junit.Test;

public class SchematicTest extends Player {

    public SchematicTest () {
        super();
    }

    @Test
    public void buildableCottageTest () {
        this.setTown();
        Town town = this.getTown();
        this.setMonument((Building)(new UniqueBuilding(BuildingType.MONUMENT)));
        town.place(2,1,Resource.WHEAT);
        town.place(1,1,Resource.GLASS);
        town.place(1,2,Resource.BRICK);
        updateBoardState();
        HashSet<Tile> buildableCottage = this.getBuildableCottage();
        assertEquals(buildableCottage.size(),3);
        assertTrue(buildableCottage.contains(town.getTile(2,1)));
        assertTrue(buildableCottage.contains(town.getTile(1,1)));
        assertTrue(buildableCottage.contains(town.getTile(1,2)));
    }

}
