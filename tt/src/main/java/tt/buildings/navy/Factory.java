package tt.buildings.navy;

import tt.*;
import tt.buildings.Navy;

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
    }
    @Override
    protected void initialize (String username) {
        this.resource = game.getTT().chooseResource(username,Resource.fullValues());
    }
    @Override
    public Resource handleTrigger (Resource roundResource,String username) {
        return game.getTT().chooseResource(username,Resource.fullValues());
    }
    @Override
    public boolean isTriggered(Resource roundResource, String username) {
        return roundResource == resource;
    }
    @Override
    public void setGame (Game game, String username) {
        this.game = game;
        initialize(username);
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
