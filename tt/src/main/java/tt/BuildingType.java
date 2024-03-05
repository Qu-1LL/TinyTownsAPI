package tt;

public enum BuildingType {
    COTTAGE(":cottage_building:"),
    YELLOW(":yellow_building:"),
    ORANGE(":orange_building:"),
    RED(":red_building:"),
    GREEN(":green_building:"),
    NAVY(":navy_building:"),
    GREY(":grey_building:"),
    MONUMENT(":monument_building:");

    private String emoji;

    private BuildingType (String emoji) {
        this.emoji = emoji;
    }
    @Override
    public String toString () {
        if (this.emoji == null) {
            return ":empty_tile:";
        }
        return emoji;
    }
}
