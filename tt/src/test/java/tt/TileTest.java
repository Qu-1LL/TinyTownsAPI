package tt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import tt.buildings.UniqueBuilding;

import org.junit.Test;

public class TileTest {
    @Test
    public void getBuildingTypeTest1 () {
        Tile tile = new Tile(0,0,new UniqueBuilding(BuildingType.YELLOW));
        BuildingType actual = tile.getBuildingType();
        BuildingType expected = BuildingType.YELLOW;
        assertEquals(expected,actual);
    }
    @Test
    public void getBuildingTypeTest2 () {
        Tile tile = new Tile(0,0);
        BuildingType actual = tile.getBuildingType();
        BuildingType expected = null;
        assertEquals(expected,actual);
    }
    @Test
    public void getResourceTest3 () {
        Tile tile = new Tile(0,0,Resource.BRICK);
        Resource actual = tile.getResource();
        Resource expected = Resource.BRICK;
        assertEquals(expected,actual);
    }
    @Test
    public void getResourceTest4 () {
        Tile tile = new Tile(0,0);
        Resource actual = tile.getResource();
        Resource expected = Resource.EMPTY;
        assertEquals(expected,actual);
    }
    @Test
    public void removeResourceTest5 () {
        Tile tile = new Tile(0,0);
        Resource actual1 = tile.removeResource();
        Resource expected1 = null;
        Resource actual2 = tile.getResource();
        Resource expected2 = Resource.EMPTY;
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
    }
    @Test
    public void removeResourceTest6 () {
        Tile tile = new Tile(0,0,Resource.BRICK);
        Resource actual1 = tile.getResource();
        Resource expected1 = Resource.BRICK;
        Resource actual2 = tile.removeResource();
        Resource expected2 = Resource.BRICK;
        Resource actual3 = tile.getResource();
        Resource expected3 = Resource.EMPTY;
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
        assertEquals(expected3,actual3);
    }
    @Test
    public void removeResourceTest7 () {
        Tile tile = new Tile(0,0,new UniqueBuilding());
        Tileable actual1 = tile.removeResource();
        Tileable expected1 = null;
        Tileable actual2 = tile.getResource();
        Tileable expected2 = null;
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
    }
    @Test
    public void isEmptyTest8 () {
        Tile tile = new Tile(0,0,new UniqueBuilding());
        boolean actual = tile.isEmpty();
        assertFalse(actual);
    }
    @Test
    public void isEmptyTest9 () {
        Tile tile = new Tile(0,0,Resource.BRICK);
        boolean actual = tile.isEmpty();
        assertFalse(actual);
    }
    @Test
    public void isEmptyTest10 () {
        Tile tile = new Tile(0,0);
        boolean actual = tile.isEmpty();
        assertTrue(actual);
    }
    @Test
    public void isEmptyTest11 () {
        Tile tile = new Tile(0,0);
        boolean actual = tile.isEmpty();
        assertTrue(actual);
    }
}
