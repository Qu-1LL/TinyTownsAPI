package tt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class BuildingTypeTest {
    @Test
    public void toStringTest1 () {
        String actual = BuildingType.COTTAGE.toString();
        String expected = ":cottage_building:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest2 () {
        String actual = BuildingType.YELLOW.toString();
        String expected = ":yellow_building:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest3 () {
        String actual = BuildingType.ORANGE.toString();
        String expected = ":orange_building:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest4 () {
        String actual = BuildingType.RED.toString();
        String expected = ":red_building:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest5 () {
        String actual = BuildingType.GREEN.toString();
        String expected = ":green_building:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest6 () {
        String actual = BuildingType.NAVY.toString();
        String expected = ":navy_building:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest7 () {
        String actual = BuildingType.GREY.toString();
        String expected = ":grey_building:";
        assertEquals(expected,actual);
    }
    @Test
    public void toStringTest8 () {
        String actual = BuildingType.MONUMENT.toString();
        String expected = ":monument_building:";
        assertEquals(expected,actual);
    }
}
