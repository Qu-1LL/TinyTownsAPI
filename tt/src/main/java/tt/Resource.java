package tt;

public enum Resource implements Tileable {
    BRICK("<:f_brick:1250677977742708797>",false),
    GLASS("<:e_glass:1250672736594821151>",false),
    STONE("<:b_stone:1250677442494861364>",false),
    WHEAT("<:c_wheat:1250664487690833982>",false),
    WOOD("<:d_wood:1250662480284221520>",false),
    EMPTY("<:a_empty:1250660017015619664>",true);

    private String emoji;
    private boolean empty;

    private Resource (String emoji,boolean empty) {
        this.emoji = emoji;
        this.empty = empty;
    }
    public boolean isEmpty () {
        return empty;
    }
    @Override
    public String toPrint () {
        return emoji;
    }
    @Override
    public String toString () {
        return emoji;
    }
}