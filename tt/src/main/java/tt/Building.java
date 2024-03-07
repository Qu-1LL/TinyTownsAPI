package tt;

public abstract class Building {
    private final String name;
    private final BuildingType type;
    private final Resource[][] schematic;

    public Building (String name, BuildingType type, Resource[][] schematic) {
        this.name = name;
        this.type = type;
        this.schematic = schematic;
    }
    public BuildingType getType () {
        return this.type;
    }
    public Resource[][] getSchematic() {
        return schematic;
        /*this code is not good, since technically references to locations
         * inside the array could edit the buildings schematic. Should be 
         * function using Arrays.copyOf(schematic) that successfully 
         * returns a double array, but copyOf only works with single arrays.
         */
    }
    public String getRules() {
        return "This is a building. It scores no points.\n" + 
        "Abuilding can be placed, and has the potential to score you points at the end of the game.\n" +
        "Buildings take up a tile on the board, and can't be moved once they are places.\n";
    }
    public int getScore(Town town) {
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
