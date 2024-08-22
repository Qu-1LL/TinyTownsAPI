package tt;

public enum BuildingType {
    GREY("<:g_grey:1250992986100338779>","grey"),
    COTTAGE("<:h_cottage:1250865474837676164>","cottage"),
    YELLOW("<:i_yellow:1250855270440112148>","yellow"),
    ORANGE("<:j_orange:1250816696369942568>","orange"),
    RED("<:k_red:1250997229754253373>","red"),
    GREEN("<:l_green:1250828044000559174>","green"),
    NAVY("<:m_navy:1250861177454596167>","navy"),
    MONUMENT("<:n_monument:1251022844935278653>","monument");

    private String emoji;
    public String name;

    private BuildingType (String emoji, String name) {
        this.emoji = emoji;
        this.name = name;
    }
    @Override
    public String toString () {
        return emoji;
    }
}
