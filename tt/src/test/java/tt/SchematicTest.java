package tt;

import tt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import tt.buildings.UniqueBuilding;

import org.junit.Test;

public class SchematicTest {

    @Test
    public void compareSchematicTest1 () {
        Resource[][] getSchematic = new Resource[2][3];
        getSchematic[0][0] = Resource.EMPTY;
        getSchematic[0][1] = Resource.STONE;
        getSchematic[0][2] = Resource.EMPTY;
        getSchematic[1][0] = Resource.WOOD;
        getSchematic[1][1] = Resource.GLASS;
        getSchematic[1][2] = Resource.WOOD;
        Town town = new Town(4);
        town.place(2,1,Resource.STONE);
        town.place(1,2,Resource.WOOD);
        town.place(2,2,Resource.GLASS);
        town.place(3,2,Resource.WOOD);

        int fx = 5;
        int fy = 5;

        boolean fail = false;
        for (int i = 0;i <= (4-getSchematic[0].length);i++) {
            for (int j = 0;j <= (4-getSchematic.length);j++) {
                fail = false;
                for (int x = 0; x < getSchematic[0].length;x++) {
                    for (int y = 0; y < getSchematic.length;y++) {
                        if (town.getTile(x+i,y+j).getResource() != getSchematic[y][x] && getSchematic[y][x] != Resource.EMPTY) {
                            fail = true;
                            break;
                        }
                    }
                    if (fail) {break;}
                }
                if (fail) {continue;}
                System.out.println("Success at i = "+i+" and j = "+j);
                fx = i;
                fy = j;
                break;
            }
            if (!fail) {break;}
        }
        assertEquals(fx,1);
        assertEquals(fy,1);
    }

}
