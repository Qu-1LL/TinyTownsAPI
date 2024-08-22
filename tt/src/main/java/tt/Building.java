package tt;

public abstract class Building implements Tileable {
    protected final String name;
    private final BuildingType type;
    private final Resource[][] schematic;
    protected boolean individual;

    static protected interface NewBuilding {
        public Building newBuilding();
    }

    public Building (String name, BuildingType type, Resource[][] schematic) {
        this.name = name;
        this.type = type;
        this.schematic = schematic;
        this.individual = true;
    }
    public BuildingType getType () {
        return type;
    }
    public boolean individual() {
        return individual;
    }
    @Override
    public String toPrint () {
        return type.toString();
    }
    public Resource[][] getSchematic() {
        return schematic;
    }
    public String getName () {
        return name;
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
    public abstract Object build ();
    public abstract String getRules();
    public abstract int getScore(Town town, int x, int y);

}
