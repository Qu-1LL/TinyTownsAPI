package tt;

public abstract class BuildingColor {
    private final BuildingType type;

    public BuildingColor (BuildingType type) {
        this.type = type;
    }
    public BuildingType getType () {
        return this.type;
    }

}
