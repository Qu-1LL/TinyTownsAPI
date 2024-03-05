package tt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ResourceTest {
    @Test
    public void toStringTest1 () {
        String actual = Resource.BRICK.toString();
        String expected = ":resource_brick:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest2 () {
        String actual = Resource.GLASS.toString();
        String expected = ":resource_glass:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest3 () {
        String actual = Resource.STONE.toString();
        String expected = ":resource_stone:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest4 () {
        String actual = Resource.WHEAT.toString();
        String expected = ":resource_wheat:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest5 () {
        String actual = Resource.WOOD.toString();
        String expected = ":resource_wood:";
        assertEquals(expected,actual);
    }
}
