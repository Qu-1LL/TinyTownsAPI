package tt.buildings.navy.buildings;

import tt.*;
import tt.buildings.navy.Navy;

public class Factory extends Navy {

    private Resource resource;

    public Factory () {
        super("Factory",new Resource[2][4],0);
        this.getSchematic()[0][0] = Resource.WOOD;
        this.getSchematic()[0][1] = Resource.EMPTY;
        this.getSchematic()[0][2] = Resource.EMPTY;
        this.getSchematic()[0][3] = Resource.EMPTY;
        this.getSchematic()[1][0] = Resource.BRICK;
        this.getSchematic()[1][1] = Resource.STONE;
        this.getSchematic()[1][2] = Resource.STONE;
        this.getSchematic()[1][3] = Resource.BRICK;
        
        this.resource = grabResource();
    }
    @Override
    public String getRules () {
        return "When this building is constructed, select a resource. "+
        "If that resource is called by another player, you may place "+ 
        "a different resource in your town if you wish.\n";
    }
    @Override
    public int getScore(Town town, int x, int y) {
        return 0;
    }

    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
