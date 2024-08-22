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
    public void getResourceTest1 () {
        Tile tile = new Tile(0,0,Resource.BRICK);
        Resource actual = tile.getResource();
        Resource expected = Resource.BRICK;
        assertEquals(expected,actual);
    }
    @Test
    public void getResourceTest2 () {
        Tile tile = new Tile(0,0);
        Resource actual = tile.getResource();
        Resource expected = Resource.EMPTY;
        assertEquals(expected,actual);
    }
    @Test
    public void removeResourceTest1 () {
        Tile tile = new Tile(0,0);
        Resource actual1 = tile.removeResource();
        Resource expected1 = null;
        Resource actual2 = tile.getResource();
        Resource expected2 = Resource.EMPTY;
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
    }
    @Test
    public void removeResourceTest2 () {
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
    public void removeResourceTest3 () {
        Tile tile = new Tile(0,0,new UniqueBuilding());
        Tileable actual1 = tile.removeResource();
        Tileable expected1 = null;
        Tileable actual2 = tile.getResource();
        Tileable expected2 = null;
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
    }
    @Test
    public void isEmptyTest1 () {
        Tile tile = new Tile(0,0,new UniqueBuilding());
        boolean actual = tile.isEmpty();
        assertFalse(actual);
    }
    @Test
    public void isEmptyTest2 () {
        Tile tile = new Tile(0,0,Resource.BRICK);
        boolean actual = tile.isEmpty();
        assertFalse(actual);
    }
    @Test
    public void isEmptyTest3 () {
        Tile tile = new Tile(0,0);
        boolean actual = tile.isEmpty();
        assertTrue(actual);
    }
    @Test
    public void isEmptyTest4 () {
        Tile tile = new Tile(0,0);
        boolean actual = tile.isEmpty();
        assertTrue(actual);
    }
    @Test
    public void getBuildingTest1 () {
        Building building = new UniqueBuilding();
        Tile tile = new Tile(0,0,building);
        Building actual = tile.getBuilding();
        Building expected = building;
        assertEquals(expected,actual);
    }
    @Test
    public void getBuildingTest2 () {
        Building building = null;
        Tile tile = new Tile(0,0);
        Building actual = tile.getBuilding();
        Building expected = building;
        assertEquals(expected,actual);
    }
    @Test
    public void getBuildingTest3 () {
        Building building = null;
        Tile tile = new Tile(0,0,building);
        Building actual = tile.getBuilding();
        Building expected = building;
        assertEquals(expected,actual);
    }
    @Test
    public void getBuildingTest4 () {
        Resource resource = Resource.WOOD;
        Tile tile = new Tile(0,0,resource);
        Building actual = tile.getBuilding();
        Building expected = null;
        assertEquals(expected,actual);
    }
    @Test
    public void buildTest1 () {
        Building building = new UniqueBuilding();
        Tile tile = new Tile(0,0);
        Tileable expected1 = Resource.EMPTY;
        Tileable actual1 = tile.getTileable();
        tile.build(building);
        Tileable expected2 = building;
        Tileable actual2 = tile.getTileable();
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
    }
    @Test
    public void buildTest2 () {
        Building building = new UniqueBuilding();
        Tile tile = new Tile(0,0,Resource.WOOD);
        Tileable expected1 = Resource.WOOD;
        Tileable actual1 = tile.getTileable();
        tile.build(building);
        Tileable expected2 = building;
        Tileable actual2 = tile.getTileable();
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
    }
}
