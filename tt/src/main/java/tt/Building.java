package tt;

public abstract class Building {
    private final String name;
    private final BuildingType type;
    private final Resource[][] schematic;
    private final int size;

    public Building (String name, BuildingType type, Resource[][] schematic, int size) {
        this.name = name;
        this.type = type;
        this.schematic = schematic;
        this.size = size;
    }
    public BuildingType getType () {
        return type;
    }
    public Resource[][] getSchematic() {
        return schematic;
    }
    public String getRules() {
        return "This is a building. It scores no points.\n" + 
        "Abuilding can be placed, and has the potential to score you points at the end of the game.\n" +
        "Buildings take up a tile on the board, and can't be moved once they are places.\n";
    }
    public int size() {
        return size;
    }
    public int getScore(Town town, int x, int y) {
        return 0;
    }
    @Override
    public String toString () {
        String uniquebuilding = name + ": \n";
        uniquebuilding += "Building type: " + type + "\n";
        uniquebuilding += this.getRules();
        for (Resource[] ra : this.getSchematic()) {
            for (Resource re : ra) {
                uniquebuilding += re.toString();
            }
        uniquebuilding += "\n";
        }
        
    return uniquebuilding;
    }

}
