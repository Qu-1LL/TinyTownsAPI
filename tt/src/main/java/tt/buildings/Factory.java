package tt.buildings;

import tt.*;

public class Factory extends Building {

    private Resource resource;

    public Factory (Resource resource) {
        super("Factory",BuildingType.NAVY,new Resource[2][4]);
        this.getSchematic()[0][0] = Resource.EMPTY;
        this.getSchematic()[0][0] = Resource.EMPTY;
        this.getSchematic()[0][0] = Resource.EMPTY;
        this.getSchematic()[0][0] = Resource.WOOD;
        this.getSchematic()[0][0] = Resource.BRICK;
        this.getSchematic()[0][0] = Resource.STONE;
        this.getSchematic()[0][0] = Resource.STONE;
        this.getSchematic()[0][0] = Resource.BRICK;
        
        this.resource = resource;
    }
    @Override
    public String getRules () {
        return "When this building is constructed, select a resource.\n"+
        "If that resource is called by another player, you may place\n"+ 
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
