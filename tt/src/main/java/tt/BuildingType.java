package tt;

public enum BuildingType {
    COTTAGE("<:cottage:1215710629181128715>"),
    YELLOW("<:yellow_building:1215710640421871716>"),
    ORANGE("<:orange_building:1215710627587428392>"),
    RED("<:red_building:1215710630162604113>"),
    GREEN("<:green_building:1215710636575694868>"),
    NAVY("<:navy_building:1215710631748042772>"),
    GREY("<:grey_building:1215710632880508929>"),
    MONUMENT("<:monument:1215710634931527710>");

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
