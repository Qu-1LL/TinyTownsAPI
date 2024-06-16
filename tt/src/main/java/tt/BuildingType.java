package tt;

public enum BuildingType {
    GREY("<:g_grey:1250992986100338779>"),
    COTTAGE("<:h_cottage:1250865474837676164>"),
    YELLOW("<:i_yellow:1250855270440112148>"),
    ORANGE("<:j_orange:1250816696369942568>"),
    RED("<:k_red:1250997229754253373>"),
    GREEN("<:l_green:1250828044000559174>"),
    NAVY("<:m_navy:1250861177454596167>"),
    MONUMENT("<:n_monument:1251022844935278653>");

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
