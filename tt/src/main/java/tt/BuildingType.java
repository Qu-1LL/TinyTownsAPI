package tt;

public enum BuildingType {
    COTTAGE("<:cottage:1215116249428922398>"),
    YELLOW("<:yellow_building:1215116250523369583>"),
    ORANGE("<:orange_building:1215116255317598248>"),
    RED("<:red_building:1215116251949441134>"),
    GREEN("<:green_building:1215116259243331605>"),
    NAVY("<:navy_building:1215116264008327178>"),
    GREY("<:grey_building:1215116257783848970>"),
    MONUMENT("<:monument:1215116256429211709>");

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
