package tt;

public abstract class Color {
    private final BuildingType type;

    public Color (BuildingType type) {
        this.type = type;
    }
    public BuildingType getType () {
        return this.type;
    }

}
